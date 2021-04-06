ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.4"
ThisBuild / organization := "io.rslowly.ctap"
ThisBuild / useSuperShell := false

val subRootPackage = "io.rslowly.ctap.project"

lazy val `ctap-project-service` = project
  .in(file("."))
  .aggregate(
    `ctap-project-application`,
    `ctap-project-infrastructure`,
    `ctap-project-domain`,
    `ctap-project-view`
  )
  .settings(name := "ctap-project-service")

lazy val `ctap-project-application` = project
  .in(file("ctap-project-application"))
  .dependsOn(`ctap-project-domain` % classpathDependencies)
  .dependsOn(`ctap-project-view` % classpathDependencies)
  .dependsOn(`ctap-project-infrastructure` % classpathDependencies)
  .settings(
    mainClass in(Compile, run) := Some("io.rslowly.ctap.project.MainApp"),
    Dependencies.application
  )

lazy val `ctap-project-infrastructure` = project
  .in(file("ctap-project-infrastructure"))
  .dependsOn(`ctap-project-domain` % classpathDependencies)
  .dependsOn(`ctap-project-view` % classpathDependencies)
  .settings(
    Dependencies.infrastructure
  )

lazy val `ctap-project-domain` = project
  .in(file("ctap-project-domain"))
  .settings(
    Dependencies.domain
  )

lazy val `ctap-project-view` = project
  .in(file("ctap-project-view"))
  .settings(
    Dependencies.view
  )

lazy val classpathDependencies: String =
  "compile->compile;test->test"

// Command aliases
addCommandAlias("run", "ctap-project-application/run")
