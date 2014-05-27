package org.positional.store.db

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global
import reactivemongo.api.MongoDriver
import scala.util.Try
import reactivemongo.api.MongoConnection
import reactivemongo.api.FailoverStrategy
import reactivemongo.api.Collection

trait PositionStore {
  val driver = new MongoDriver

  val uri = "mongodb://martosoler:Unqu1ll0!@ds045679.mongolab.com:45679/followme"

  val connection: Try[MongoConnection] =
    MongoConnection.parseURI(uri).map {
      parsedUri => driver.connection(parsedUri)
    }

  val db = connection.map(_.db("followme")).get
  
  def collection(name: String): Collection = db(name)
}