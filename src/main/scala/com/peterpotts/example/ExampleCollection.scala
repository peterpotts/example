package com.peterpotts.example

import scala.reflect.ClassTag
import scalaz.Scalaz._

trait ExampleCollection {
  self: ExampleMonad with ExamplePrimitive with ExampleTuple =>

  def exampleList[A](exampleA: Example[A], size: Int = defaultSize): Example[List[A]] =
    List.fill(size)(exampleA).sequence[Example, A]

  def exampleArray[A: ClassTag](exampleA: Example[A], size: Int = defaultSize): Example[Array[A]] =
    exampleList(exampleA, size).map(_.toArray[A])

  def exampleVector[A](exampleA: Example[A], size: Int = defaultSize): Example[Vector[A]] =
    exampleList(exampleA, size).map(_.toVector)

  def exampleMap[A, B](exampleA: Example[A], exampleB: Example[B], size: Int = defaultSize): Example[Map[A, B]] =
    List.fill(size)(exampleTuple2(exampleA, exampleB)).sequence[Example, (A, B)].map(_.toMap)

  def exampleBytes(size: Int): Example[Array[Byte]] = exampleArray(exampleByte, size)
}
