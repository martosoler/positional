import sbt._
import Keys._

object BuildSettings {
  val buildVersion = "0.0.1-SNAPSHOT"
    
    val buildSettings = Defaults.defaultSettings ++ Seq(
	    organization := "org.reactivemongo",
	    version := buildVersion,
	    scalaVersion := "2.10.3",
		scalacOptions ++= Seq("-unchecked", "-deprecation", "-encoding", "utf8"))
}

object Resolvers {
  val typesafe = Seq(
    "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
    "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/")
  val resolversList = typesafe
}

object DependencieVersions {
	val akkaVersion = "2.3.0"
	val sprayVersion = "1.3.1"
	val reactiveMongoVersion = "0.10.5.akka23-SNAPSHOT"
}

object RestAPIDependencies {
	val akkaActor = Seq(
			"com.typesafe.akka" %% "akka-actor" % DependencieVersions.akkaVersion,
			"com.typesafe.akka"  %%  "akka-testkit"  % DependencieVersions.akkaVersion   % "test"
		)

	val sprayRestDependencies = Seq(
			"io.spray" %   "spray-can"     % DependencieVersions.sprayVersion,
			"io.spray" %   "spray-routing" % DependencieVersions.sprayVersion,
		    "io.spray" %%  "spray-json"    % "1.2.6",
			"io.spray" %   "spray-testkit" % DependencieVersions.sprayVersion  % "test"
		)

	val specs = "org.specs2" %%  "specs2-core"   % "2.3.7" % "test"
}

object PositionalBuild extends Build {
  import BuildSettings._
  import Resolvers._
  import DependencieVersions._

  lazy val positional = 
    Project("positional", file("."), settings = buildSettings)
    	.aggregate(position_store, position_provider, position_receiver)
  
  lazy val position_store = 
    Project("position-store", file("position-store"),
        settings = buildSettings ++ Seq(
            resolvers := resolversList,
            libraryDependencies ++= Seq(
                "org.reactivemongo" %% "reactivemongo" % DependencieVersions.reactiveMongoVersion
                )
            ))

  lazy val position_provider = 
    Project("position-provider", file("position-provider"),
        settings = buildSettings ++ Seq(
            resolvers := resolversList,
            libraryDependencies ++= Seq(
				RestAPIDependencies.specs) ++ RestAPIDependencies.sprayRestDependencies ++
			    RestAPIDependencies.akkaActor
            )).dependsOn(position_store)


  lazy val position_receiver = 
    Project("position-receiver", file("position-receiver"),
        settings = buildSettings ++ Seq(
            resolvers := resolversList,
            libraryDependencies ++= Seq(
				RestAPIDependencies.specs) ++ RestAPIDependencies.sprayRestDependencies ++
			    RestAPIDependencies.akkaActor
				
            )).dependsOn(position_store)
}
