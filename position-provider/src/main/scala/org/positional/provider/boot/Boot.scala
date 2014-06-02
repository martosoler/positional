package org.positional.provider.boot

import akka.actor.ActorSystem
import akka.actor.Props
import org.positional.provider.controller.PositionProviderController
import akka.util.Timeout
import akka.io.IO
import spray.can.Http
import scala.concurrent.duration._
import akka.pattern.ask

object Boot extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("position-provider")
  
  val controller = system.actorOf(Props[PositionProviderController], "position-provider-controller")
  
  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(controller, interface = "localhost", port = 8092)
}