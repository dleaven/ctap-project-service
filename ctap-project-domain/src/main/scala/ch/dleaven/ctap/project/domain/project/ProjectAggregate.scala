package ch.dleaven.ctap.project.domain.project

import akka.actor.typed.Behavior
import akka.cluster.sharding.typed.scaladsl.EntityTypeKey
import akka.persistence.typed.PersistenceId
import akka.persistence.typed.scaladsl.{EventSourcedBehavior, ReplyEffect}
import ch.dleaven.ctap.project.domain.project.ProjectProtocol.{ProjectCommand, ProjectEvent}
import ch.dleaven.ctap.project.domain.project.ProjectState.{EmptyProject, Project}

object ProjectAggregate {

  def apply(entityId: String, persistenceId: PersistenceId): Behavior[ProjectCommand] =
    EventSourcedBehavior.withEnforcedReplies[ProjectCommand, ProjectEvent, Project](
      persistenceId = persistenceId,
      commandHandler = commandHandler,
      eventHandler = eventHandler,
      emptyState = EmptyProject
    )

  val typeKey: EntityTypeKey[ProjectCommand]                                          = EntityTypeKey[ProjectCommand]("Project")
  val commandHandler: (Project, ProjectCommand) => ReplyEffect[ProjectEvent, Project] = (state, command) => state.applyCommand(command)
  val eventHandler: (Project, ProjectEvent) => Project                                = (state, event) => state.applyEvent(event)
}
