import sbt.Keys._
import sbt._

object Dependencies {

  val akkaVersion = "2.6.10"
  val akkaHttpVersion = "10.2.2"
  val akkaPersistenceJdbcVersion = "4.0.0"
  val flywayVersion = "7.3.2"
  val logbackVersion = "1.2.3"
  val postgreSqlVersion = "42.2.18"
  val scalaTestVersion = "3.2.3"

  object Compile {
    val akkaActorTyped = "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
    val akkaStreamTyped = "com.typesafe.akka" %% "akka-stream-typed" % akkaVersion
    val akkaPersistenceTyped = "com.typesafe.akka" %% "akka-persistence-typed" % akkaVersion
    val akkaPersistenceQuery = "com.typesafe.akka" %% "akka-persistence-query" % akkaVersion
    val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
    val flyway = "org.flywaydb" % "flyway-core" % flywayVersion
    val logback = "ch.qos.logback" % "logback-classic" % logbackVersion
    val postgreSql = "org.postgresql" % "postgresql" % postgreSqlVersion
  }

  object Test {
    val akkaActorTestkitTyped = "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion      % "test"
    val scalaTest             = "org.scalatest"     %% "scalatest"                % scalaTestVersion % "test"
  }

  val l = libraryDependencies

  val application = l ++= Seq(
    Compile.akkaActorTyped,
    Compile.akkaStreamTyped,
    Compile.akkaHttp,
    Compile.logback,
    Test.akkaActorTestkitTyped,
    Test.scalaTest
  )

  val domain = l ++= Seq(
    Compile.akkaActorTyped,
    Compile.akkaPersistenceTyped,
    Test.akkaActorTestkitTyped,
    Test.scalaTest
  )

  val infrastructure = l ++= Seq(
    Compile.akkaPersistenceQuery,
    Compile.flyway,
    Compile.postgreSql,
    Test.scalaTest
  )

  val view = l ++= Seq(
    Test.scalaTest
  )
}
