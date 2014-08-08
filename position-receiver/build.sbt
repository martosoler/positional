organization  := "org.positional"

version       := "0.1"

scalaVersion  := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "spray repo" at "http://repo.spray.io"

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= {
  val positionStoreV = "0.1"
  val akkaV = "2.3.0"
  val sprayV = "1.3.1"  
  Seq(
    "io.spray"            %   "spray-can"     % sprayV,
    "io.spray"            %   "spray-routing" % sprayV,
    "io.spray" 			  %%  "spray-json" 	  % "1.2.6",
    "io.spray"            %   "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.positional"      %%  "position-store" % "0.1",
    "org.reactivemongo" %% "reactivemongo" % "0.10.5.akka23-SNAPSHOT"
  )
}

Revolver.settings
