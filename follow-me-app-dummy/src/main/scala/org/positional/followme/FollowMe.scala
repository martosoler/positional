package org.positional.followme

import scalaj.http.Http
import scalaj.http.HttpOptions

object FollowMe extends App {

  val data = "{\"latitude\": \"martin\", \"longitude\":\"nanci\"}"
    
  val response = Http.postData("http://127.0.0.1:8082/here", data)
  	.header("content-type", "application/json").asString
  	
  print(response)
}