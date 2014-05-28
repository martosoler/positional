package org.positional.store

import akka.actor.Actor
import akka.event.Logging
import org.positional.store.message.StorePosition
import org.positional.store.message.RetrievePosition
import org.positional.store.db.PositionStore

class PositionStoreActor extends Actor with PositionStore {
  val log = Logging(context.system, this)
  
  def receive = {
    case m: StorePosition =>
    	log.info("Store the position: " + m)
    	
    case m: RetrievePosition => 
      	log.info("Retrieve position for user: " + m.id)
      	retrievePositions(m.id)
  }
}