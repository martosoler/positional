package org.positional.store.db

import reactivemongo.bson.BSONDocumentReader
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentWriter

case class Position(id: String, latitude: String, longitude: String)

object Position {
  implicit object PositionReader extends BSONDocumentReader[Position] {
    def read(doc: BSONDocument): Position = {
      val id = doc.getAs[String]("id").get
      val latitude = doc.getAs[String]("latitude").get
      val longitude = doc.getAs[String]("longitude").get

      Position(id, latitude, longitude);
    }
  }

  implicit object PositionWriter extends BSONDocumentWriter[Position] {
    def write(pos: Position): BSONDocument = {
      BSONDocument(
        "id" -> pos.id,
        "latitude" -> pos.latitude,
        "longitude" -> pos.longitude)
    }
  }
}