package ch.dleaven.ctap.project.domain.project

import akka.actor.typed.Behavior
import akka.persistence.typed.PersistenceId
import akka.persistence.typed.scaladsl.{Effect, EventSourcedBehavior, ReplyEffect}

object ProjectAggregate {
  final case class State()

  def apply(entityId: String, persistenceId: PersistenceId): Behavior[Command] =
    EventSourcedBehavior.withEnforcedReplies[Command, Event, State](
      persistenceId = persistenceId,
      commandHandler = commandHandler,
      eventHandler = eventHandler,
      emptyState = State()
    )

  val commandHandler: (State, Command) => ReplyEffect[Event, State] = { (state, command) =>
    command match {
      case AddProjectGroup() => Effect
        .persist(ProjectGroupAdded(name = "Hans"))
        .thenNoReply()
      case AddProject() => Effect
        .persist(ProjectAdded(name = "Hans"))
        .thenNoReply()
      case _ => Effect
        .unhandled
        .thenNoReply()
    }
  }

  val eventHandler: (State, Event) => State = { (state, event) =>
    event match {
      case ProjectGroupAdded(_) => State()
      case ProjectAdded(_) => State()
    }
  }
}
