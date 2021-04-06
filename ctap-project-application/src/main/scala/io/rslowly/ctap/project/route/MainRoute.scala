package io.rslowly.ctap.project.route

import akka.actor.typed.ActorSystem
import akka.util.Timeout

import scala.concurrent.duration._

class MainRoute()(implicit system: ActorSystem[_]) {

  import akka.actor.typed.scaladsl.AskPattern.schedulerFromActorSystem
  import akka.actor.typed.scaladsl.AskPattern.Askable

  implicit val timeout: Timeout = 3.seconds



}
