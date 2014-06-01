package org.positional.provider.dispatcher

import spray.routing.HttpService

trait PositionProviderRouting extends HttpService {

  val route = {
    get {
      
      pathSingleSlash {
        complete("Hello world")
      } ~
      path("users/1/positions") {
        complete("Here")
      }
    }
  }
}