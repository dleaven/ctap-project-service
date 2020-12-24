import sbt.Keys._
import sbt._

object Dependencies {

  val akkaVersion                = "2.6.13"
  val akkaHttpVersion            = "10.2.4"
  val akkaPersistenceJdbcVersion = "4.0.0"
  val borerVersion               = "1.6.3"
  val flywayVersion              = "7.3.2"
  val logbackVersion             = "1.2.3"
  val postgreSqlVersion          = "42.2.18"
  val scalaTestVersion           = "3.2.3"

  object Compile {
    val akkaActorTyped           = "com.typesafe.akka" %% "akka-actor-typed"            % akkaVersion
    val akkaClusterShardingTyped = "com.typesafe.akka" %% "akka-cluster-sharding-typed" % akkaVersion
    val akkaJacksonSerialization = "com.typesafe.akka" %% "akka-serialization-jackson"  % akkaVersion
    val akkaStreamTyped          = "com.typesafe.akka" %% "akka-stream-typed"           % akkaVersion
    val akkaPersistenceTyped     = "com.typesafe.akka" %% "akka-persistence-typed"      % akkaVersion
    val akkaPersistenceQuery     = "com.typesafe.akka" %% "akka-persistence-query"      % akkaVersion
    val akkaHttp                 = "com.typesafe.akka" %% "akka-http"                   % akkaHttpVersion
    val borerCore                = "io.bullet"         %% "borer-core"                  % borerVersion
    val borerDerivation          = "io.bullet"         %% "borer-derivation"            % borerVersion
    val borerCompatAkka          = "io.bullet"         %% "borer-compat-akka"           % borerVersion
    val flyway                   = "org.flywaydb"       % "flyway-core"                 % flywayVersion
    val logback                  = "ch.qos.logback"     % "logback-classic"             % logbackVersion
    val postgreSql               = "org.postgresql"     % "postgresql"                  % postgreSqlVersion
  }

  object Test {
    val akkaActorTestkitTyped  = "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion      % "test"
    val akkaPersistenceTestkit = "com.typesafe.akka" %% "akka-persistence-testkit" % akkaVersion      % "test"
    val scalaTest              = "org.scalatest"     %% "scalatest"                % scalaTestVersion % "test"
  }

  val l = libraryDependencies

  val application = l ++= Seq(
    Compile.akkaActorTyped,
    Compile.akkaStreamTyped,
    Compile.akkaHttp,
    Compile.borerCore,
    Compile.borerDerivation,
    Compile.borerCompatAkka,
    Compile.logback,
    Test.akkaActorTestkitTyped,
    Test.scalaTest
  )

  val domain = l ++= Seq(
    Compile.akkaActorTyped,
    Compile.akkaJacksonSerialization,
    Compile.akkaClusterShardingTyped,
    Compile.akkaPersistenceTyped,
    Compile.borerCore,
    Compile.borerDerivation,
    Compile.borerCompatAkka,
    Test.akkaActorTestkitTyped,
    Test.akkaPersistenceTestkit,
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
