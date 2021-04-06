package io.rslowly.ctap.project.domain

import io.rslowly.ctap.project.domain.project.ProjectProtocol.{ProjectCommand, ProjectEvent}
import io.rslowly.ctap.project.domain.project.ProjectState.Project
import io.bullet.borer.Codec
import io.bullet.borer.derivation.MapBasedCodecs.deriveAllCodecs

trait Codecs {

  import io.bullet.borer.compat.akka._

  implicit lazy val project: Codec[Project]               = deriveAllCodecs
  implicit lazy val projectCommand: Codec[ProjectCommand] = deriveAllCodecs
  implicit lazy val projectEvent: Codec[ProjectEvent]     = deriveAllCodecs
}
