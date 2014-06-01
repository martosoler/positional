organization  := "org.positional"

version       := "0.1"

scalaVersion  := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  Seq(
    "org.scalaj" %% "scalaj-http" % "0.3.15"
  )
}
