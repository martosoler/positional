package org.positional.provider.controller

import spray.routing.HttpService
import spray.routing.Route

trait PositionProviderRouting extends HttpService {

  val route : Route = {
    get {
      pathPrefix("users") {
        path(IntNumber) { userId => 
          complete("Hello user: " + userId)
        }
      }
    } 
  }
}