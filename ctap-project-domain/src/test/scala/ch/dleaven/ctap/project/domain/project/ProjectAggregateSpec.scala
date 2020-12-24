package ch.dleaven.ctap.project.domain.project

import akka.Done
import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import akka.pattern.StatusReply
import akka.persistence.testkit.scaladsl.EventSourcedBehaviorTestKit
import akka.persistence.typed.PersistenceId
import ch.dleaven.ctap.project.domain.project.ProjectProtocol.{ProjectCommand, ProjectEvent}
import ch.dleaven.ctap.project.domain.project.ProjectState.Project
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funspec.AnyFunSpecLike

class ProjectAggregateSpec
    extends ScalaTestWithActorTestKit(EventSourcedBehaviorTestKit.config)
    with AnyFunSpecLike
    with BeforeAndAfterEach {

  private val estk =
    EventSourcedBehaviorTestKit[ProjectCommand, ProjectEvent, Project](system, ProjectAggregate("1", PersistenceId("Project", "1")))

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    estk.clear()
  }

  describe("when state is EmptyProject") {

    it("should create OpenProject") {
      // arrange
      val name = "name"
      // act
      val result = estk.runCommand[StatusReply[Done]](ProjectProtocol.CreateProject(name, _))
      // assert
      result.event shouldBe ProjectProtocol.ProjectCreated(name)
      result.reply shouldBe StatusReply.Ack
      result.stateOfType[ProjectState.OpenProject].name shouldBe name
    }
  }
}
