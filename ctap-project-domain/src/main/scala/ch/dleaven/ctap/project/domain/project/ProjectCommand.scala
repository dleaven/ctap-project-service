package ch.dleaven.ctap.project.domain.project

sealed trait Command
case class AddProjectGroup() extends Command
case class AddProject() extends Command
