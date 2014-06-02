package org.positional.provider.controller

import akka.actor._

class PositionProviderController extends Actor with PositionProviderRouting {

  def actorRefFactory = context
  
  def receive = runRoute(route)
}