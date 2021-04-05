package ch.dleaven.ctap.project.domain

import akka.serialization.Serializer
import ch.dleaven.ctap.project.domain.project.ProjectProtocol.{ProjectCommand, ProjectEvent}
import ch.dleaven.ctap.project.domain.project.ProjectState.Project
import io.bullet.borer.{Decoder, Json}

import scala.reflect.ClassTag

class JsonSerializer() extends Serializer with Codecs {

  override def identifier: Int = 101

  override def includeManifest: Boolean = true

  override def toBinary(o: AnyRef): Array[Byte] =
    o match {
      case x: Project => Json.encode(x).toByteArray
      case x: ProjectCommand => Json.encode(x).toByteArray
      case x: ProjectEvent => Json.encode(x).toByteArray
      case _ =>
        throw new RuntimeException(s"does not support encoding of $o")
    }

  override def fromBinary(bytes: Array[Byte], manifest: Option[Class[_]]): AnyRef = {
    def fromBinary[T: ClassTag: Decoder]: Option[T] = {
      val clazz = scala.reflect.classTag[T].runtimeClass
      if (clazz.isAssignableFrom(manifest.get))
        Some(Json.decode(bytes).to[T].value)
      else None
    }

    {
      fromBinary[Project] orElse
        fromBinary[ProjectCommand] orElse
        fromBinary[ProjectEvent]
    }.getOrElse {
      throw new RuntimeException(
        s"does not support decoding of ${manifest.get}"
      )
    }
  }
}
