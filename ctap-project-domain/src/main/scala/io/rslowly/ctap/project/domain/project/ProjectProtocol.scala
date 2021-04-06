package io.rslowly.ctap.project.domain.project

import akka.Done
import akka.actor.typed.ActorRef
import akka.pattern.StatusReply
import io.rslowly.ctap.project.domain.JsonSerializable

object ProjectProtocol {

  sealed trait ProjectCommand                                                        extends JsonSerializable
  final case class CreateProject(name: String, replyTo: ActorRef[StatusReply[Done]]) extends ProjectCommand

  sealed trait ProjectEvent                     extends JsonSerializable
  final case class ProjectCreated(name: String) extends ProjectEvent
}
