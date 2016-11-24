package com.peterpotts.example

import scala.collection.immutable.IndexedSeq
import scala.reflect.ClassTag
import scalaz.Scalaz._

trait ExampleCollection {
  self: ExampleMonad with ExamplePrimitive with ExampleTuple =>

  def examplePick[T](values: IndexedSeq[T]): Example[T] = exampleInt(values.size).map(values(_))

  def exampleShuffle[T](examples: Example[T]*): Example[T] = exampleInt(examples.size).flatMap(examples(_))

  def exampleList[A](exampleA: Example[A], size: Int = defaultSize): Example[List[A]] =
    List.fill(size)(exampleA).sequence[Example, A]

  def exampleList[A](exampleA: Example[A], sizes: IndexedSeq[Int]): Example[List[A]] =
    examplePick(sizes).flatMap(size => exampleList(exampleA, size))

  def exampleArray[A: ClassTag](exampleA: Example[A], size: Int = defaultSize): Example[Array[A]] =
    exampleList(exampleA, size).map(_.toArray[A])

  def exampleArray[A: ClassTag](exampleA: Example[A], sizes: IndexedSeq[Int]): Example[Array[A]] =
    examplePick(sizes).flatMap(size => exampleArray(exampleA, size))

  def exampleVector[A](exampleA: Example[A], size: Int = defaultSize): Example[Vector[A]] =
    exampleList(exampleA, size).map(_.toVector)

  def exampleVector[A: ClassTag](exampleA: Example[A], sizes: IndexedSeq[Int]): Example[Vector[A]] =
    examplePick(sizes).flatMap(size => exampleVector(exampleA, size))

  def exampleMap[A, B](exampleA: Example[A], exampleB: Example[B], size: Int = defaultSize): Example[Map[A, B]] =
    List.fill(size)(exampleTuple2(exampleA, exampleB)).sequence[Example, (A, B)].map(_.toMap)

  def exampleMap[A, B](exampleA: Example[A], exampleB: Example[B], sizes: IndexedSeq[Int]): Example[Map[A, B]] =
    examplePick(sizes).flatMap(size => exampleMap(exampleA, exampleB, size))

  val exampleBytes: Example[Array[Byte]] = exampleArray(exampleByte, defaultSize)

  def exampleBytes(size: Int): Example[Array[Byte]] = exampleArray(exampleByte, size)

  def exampleBytes(sizes: IndexedSeq[Int]): Example[Array[Byte]] =
    examplePick(sizes).flatMap(exampleBytes(_))
}
