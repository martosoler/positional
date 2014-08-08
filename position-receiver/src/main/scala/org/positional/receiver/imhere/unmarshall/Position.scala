package org.positional.receiver.imhere.unmarshall

import spray.httpx.unmarshalling._
import spray.util._
import spray.http._
import spray.json._
import DefaultJsonProtocol._
import spray.httpx.SprayJsonSupport

case class Position (id: String, latitude: String, longitude: String, timestamp: Option[String])

object JsonPositionProtocol extends DefaultJsonProtocol  with SprayJsonSupport {
  implicit val colorFormat = jsonFormat4(Position)
}

