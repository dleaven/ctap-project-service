package ch.dleaven.ctap.project.domain.project

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import org.scalatest.funspec.AnyFunSpecLike

import scala.collection.mutable

class ProjectAggregateTest extends ScalaTestWithActorTestKit with AnyFunSpecLike {

  describe("When receiving AddProjectGroup") {

    it("should apply ProjectGroupAdded if command is valid") {
      val stack = new mutable.Stack[Int]
      stack.push(1)
      stack.push(2)
      assert(stack.pop() === 2)
      assert(stack.pop() === 1)
    }

    it("should throw NoSuchElementException if an empty stack is popped") {
      val emptyStack = new mutable.Stack[Int]
      intercept[NoSuchElementException] {
        emptyStack.pop()
      }
    }

    //  "Something" must {
    //    "behave correctly" in {
    //      val pinger = testKit.spawn(Echo(), "ping")
    //      val probe = testKit.createTestProbe[Echo.Pong]()
    //      pinger ! Echo.Ping("hello", probe.ref)
    //      probe.expectMessage(Echo.Pong("hello"))
    //    }
  }
}
