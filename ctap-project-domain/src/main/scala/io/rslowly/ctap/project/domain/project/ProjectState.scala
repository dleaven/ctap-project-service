package io.rslowly.ctap.project.domain.project

import akka.pattern.StatusReply
import akka.persistence.typed.scaladsl.Effect
import io.rslowly.ctap.project.domain.JsonSerializable
import io.rslowly.ctap.project.domain.project.ProjectProtocol.{CreateProject, ProjectCommand, ProjectCreated, ProjectEvent}

object ProjectState {
  type ReplyEffect = akka.persistence.typed.scaladsl.ReplyEffect[ProjectEvent, Project]

  sealed trait Project extends JsonSerializable {
    def applyCommand(cmd: ProjectCommand): ReplyEffect
    def applyEvent(event: ProjectEvent): Project
  }

  final case object EmptyProject extends Project {
    override def applyCommand(cmd: ProjectCommand): ReplyEffect =
      cmd match {
        case CreateProject(name, replyTo) => Effect.persist(ProjectCreated(name)).thenReply(replyTo)(_ => StatusReply.Ack)
        case _                            => Effect.unhandled.thenNoReply()
      }

    override def applyEvent(event: ProjectEvent): Project =
      event match {
        case ProjectCreated(name) => OpenProject(name)
        case _                    => throw new IllegalArgumentException(s"unexpected event [$event] in state [EmptyProject]")
      }
  }

  final case class OpenProject(name: String) extends Project {
    override def applyCommand(cmd: ProjectCommand): ReplyEffect = ???

    override def applyEvent(event: ProjectEvent): Project =
      event match {
        case _ => throw new IllegalArgumentException(s"unexpected event [$event] in state [OpenProject]")
      }

  }
}
