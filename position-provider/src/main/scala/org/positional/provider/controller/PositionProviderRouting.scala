package org.positional.provider.controller

import spray.routing.HttpService
import spray.routing.Route
import spray.http.ContentType
import spray.http.MediaTypes

trait PositionProviderRouting extends HttpService {

  val route: Route = {
    get {
      respondWithMediaType(MediaTypes.`application/json`) {
        pathPrefix("users") {
          path(IntNumber) { userId =>
            // This returns all the positions of the users that
            // correspond to his/her the corresponding trip
            path("positions" / IntNumber) { tripId =>
              complete("These are the positions for user: " + userId + " at trip " + tripId)
            } ~
	          // This returns the last registered position of the user
	          path("last-position") {
	            complete("This is the last position for user: " + userId)
	          } ~
	          path("info") {
	            complete("This is the information for user: " + userId)
	          }
          }
        }
      }
    }
  }
}