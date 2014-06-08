package org.positional.store

import akka.actor.ActorSystem
import akka.actor.Props
import org.positional.store.message.StorePosition
import org.positional.store.message.RetrievePosition
import org.positional.store.db.Position
import scala.util.Random

object Boot extends App {
	// we need an ActorSystem to host our application in
  implicit val system = ActorSystem("position-store")
  
  // create and start our service actor
  val positionStoreService = system.actorOf(Props[PositionStoreActor], "position-store-service")
  
  positionStoreService ! StorePosition(
      Position(Random.nextInt.toString, System.nanoTime().toString, System.currentTimeMillis().toString))
  
  positionStoreService ! RetrievePosition("1")
  
  //system.shutdown
}