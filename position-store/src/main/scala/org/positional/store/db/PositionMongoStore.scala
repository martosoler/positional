package org.positional.store.db

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global
import reactivemongo.api.MongoDriver
import scala.util.Try
import reactivemongo.api._
import reactivemongo.bson.BSONDocument
import play.api.libs.iteratee.Iteratee
import scala.concurrent.Future
import akka.actor.ActorSystem
import com.typesafe.config._
import scala.util.Failure
import scala.util.Success
import reactivemongo.bson.BSONObjectID
import scala.collection.immutable.List
import reactivemongo.core.nodeset.Authenticate

object PositionMongoStore {
  implicit val system: ActorSystem = ActorSystem("position-store")

  val driver = new MongoDriver(system)

  val dbName = "followme"
  val username = "martosoler"
  val password = "Unqu1ll0!"
  val credentials = List(Authenticate(dbName, username, password))
  val servers = List("ds045679.mongolab.com:45679")
  val connection = driver.connection(servers, nbChannelsPerNode = 5, authentications = credentials)
}

trait PositionMongoStore {
  import PositionMongoStore._

  val db = connection.db("followme")

  val positions = db("positions")

  def retrievePositions(id: String) = {

    val query = BSONDocument("id" -> id)

    positions.
      find(query).
      cursor[BSONDocument].
      enumerate().apply(Iteratee.foreach { doc =>
        println("found document: " + BSONDocument.pretty(doc))
      })
  }

  def storePosition(pos: Position) = {

    val future = positions.insert(pos)

    future.onComplete {
      case Failure(e) => throw e
      case Success(lastError) => {
        println("Sucessfully inserted position: " + pos)
      }
    }
  }
}