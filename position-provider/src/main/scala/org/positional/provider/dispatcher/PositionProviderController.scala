package org.positional.provider.dispatcher

import akka.actor._

class PositionProviderController extends Actor with PositionProviderRouting {

  def actorRefFactory = context
  
  def receive = runRoute(route)
}