package org.positional.receiver

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import org.positional.receiver.imhere.ImHereServiceActorRouting

object Boot extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("on-spray-can")

  // create and start our service actor
  val imHereService = system.actorOf(Props[ImHereServiceActorRouting], "im-here-service")

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(imHereService, interface = "localhost", port = 8082)
}
