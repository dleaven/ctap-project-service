package ch.dleaven.ctap.project.domain.project

sealed trait Event
case class ProjectGroupAdded(name: String) extends Event
case class ProjectAdded(name: String) extends Event
