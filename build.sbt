name := "ctap-project-service"

organization := "ch.dleaven.ctap"

version := "0.1"

scalaVersion := "2.13.3"

lazy val akkaVersion = "2.6.8"

libraryDependencies ++= Seq(
  // Compile dependencies
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.lightbend.akka" %% "akka-persistence-jdbc" % "4.0.0",
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence-query" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % "10.2.0-M1",
  "org.flywaydb" % "flyway-core" % "6.5.3",
  "org.postgresql" % "postgresql" % "42.2.12",
  // Test dependencies
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % "3.1.0" % Test
)
