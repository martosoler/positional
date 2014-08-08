package org.positional.receiver.imhere

import spray.routing.HttpService
import spray.http.MediaTypes
import scala.util.parsing.json.JSONObject
import org.positional.receiver.imhere.unmarshall.Position
import org.positional.receiver.imhere.unmarshall.JsonPositionProtocol._
import akka.actor.Props
import org.positional.store.PositionStoreActor
import org.positional.store.message.StorePosition

trait ImHereService extends HttpService {

  // we use the enclosing ActorContext's or ActorSystem's dispatcher for our Futures and Scheduler
  //implicit def executionContext = actorRefFactory.dispatcher

  val route = {
    get {
      pathSingleSlash {
        complete("Hello world")
      }
    } ~
      (post & path("here")) {
        entity(as[Position]) { pos =>
          // send message to actor for persistence
          val positionStoreActor = actorRefFactory.actorOf(Props[PositionStoreActor], "position-store-service")

          positionStoreActor ! StorePosition(
            org.positional.store.db.Position(pos.id, pos.latitude, pos.longitude))

          // respond with ok
          respondWithMediaType(MediaTypes.`application/json`) {
            complete(s"Latitude received: ${pos.latitude} - longitude: ${pos.longitude}")
          }
        }
      }
  }
}