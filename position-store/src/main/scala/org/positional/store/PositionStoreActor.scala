package org.positional.store

import akka.actor.Actor
import akka.event.Logging
import org.positional.store.message.StorePosition

class PositionStoreActor extends Actor {
  val log = Logging(context.system, this)
  
  def receive = {
    case m: StorePosition =>
    	log.info("Store the position: " + m)
  }
}