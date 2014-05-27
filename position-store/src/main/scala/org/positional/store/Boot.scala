package org.positional.store

import akka.actor.ActorSystem
import akka.actor.Props
import org.positional.store.message.StorePosition

object Boot extends App {
	// we need an ActorSystem to host our application in
  implicit val system = ActorSystem("position-store")
  
  // create and start our service actor
  val positionStoreService = system.actorOf(Props[PositionStoreActor], "position-store-service")
  
  positionStoreService ! StorePosition("123", "123")
}