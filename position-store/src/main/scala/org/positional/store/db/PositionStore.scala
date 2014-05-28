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

trait PositionStore {
  implicit val system: ActorSystem = ActorSystem("position-store")

  val driver = new MongoDriver(system)

  val cfg = ConfigFactory.load()
  
  val uri = "mongodb://martosoler:Unqu1ll0!@ds045679.mongolab.com:45679/followme"
  // val uri = "mongodb://" + cfg.getString("mongo.db.user") + ":" + cfg.getString("mongo.db.pass") + "@" + cfg.getString("mongo.db.host") +
  //  ":" + cfg.getString("mongo.db.port") + "/" + cfg.getString("mongo.db.name")
  
  /*
  val uri = for {
    user <- cfg.getString("mongo.db.user")
    pass <- cfg.getString("mongo.db.pass")
    server <- cfg.getString("mongo.db.server")
    port <- cfg.getString("mongo.db.port")
    databaseName <- cfg.getString("mongo.db.name")
  } yield { 
    "mongodb://$user:$pass@$server:$port/$databaseName"
  }
  * 
  */
  
  val connection: Try[MongoConnection] =
    MongoConnection.parseURI(uri).map {
      parsedUri => driver.connection(parsedUri)
    }

  val db = connection.map(_.db("followme")).get

  def retrievePositions(id: String) = {
    // Select only the documents which field 'firstName' equals 'Jack'
    val query = BSONDocument("id" -> id)

    val collection = db("positions")

    /* Let's run this query then enumerate the response and print a readable
   * representation of each document in the response */
    collection.
      find(query).
      cursor[BSONDocument].
      enumerate().apply(Iteratee.foreach { doc =>
        println("found document: " + BSONDocument.pretty(doc))
      })

  }
}