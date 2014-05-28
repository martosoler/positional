organization  := "org.positional"

version       := "0.1"

scalaVersion  := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= {
  val akkaV = "2.3.2"
  val reactiveMongoV = "0.10.0"
  Seq(
    "org.reactivemongo"   %%  "reactivemongo" % reactiveMongoV
  )
}
