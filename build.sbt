ThisBuild / version       := "0.0.1-SNAPSHOT"
ThisBuild / scalaVersion  := "2.13.3"
ThisBuild / organization  := "ch.dleaven.ctap"
ThisBuild / useSuperShell := false

val subRootPackage = "ch.dleaven.ctap.project"

lazy val `ctap-project-service` = project
  .in(file("."))
  .aggregate(application, infrastructure, domain, view)
  .settings(name := "ctap-project-service")

lazy val application = project
  .in(file("ctap-project-application"))
  .dependsOn(domain % classpathDependencies)
  .dependsOn(view % classpathDependencies)
  .dependsOn(infrastructure % classpathDependencies)
  .settings(
    mainClass in (Compile, run) := Some(s"$subRootPackage.MainApp"),
    Dependencies.application
  )

lazy val infrastructure = project
  .in(file("ctap-project-infrastructure"))
  .dependsOn(domain % classpathDependencies)
  .dependsOn(view % classpathDependencies)
  .settings(
    Dependencies.infrastructure
  )

lazy val domain = project
  .in(file("ctap-project-domain"))
  .settings(
    Dependencies.domain
  )

lazy val view = project
  .in(file("ctap-project-view"))
  .settings(
    Dependencies.view
  )

lazy val classpathDependencies: String =
  "compile->compile;test->test"

// Command aliases
addCommandAlias("run", "application/run")
