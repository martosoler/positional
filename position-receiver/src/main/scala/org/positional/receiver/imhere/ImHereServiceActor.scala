package org.positional.receiver.imhere

import akka.actor._

class ImHereServiceActorRouting extends Actor with ImHereService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing,
  // timeout handling or alternative handler registration
  def receive = runRoute(route)
}