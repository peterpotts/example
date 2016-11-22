package com.peterpotts.example

import scalaz.Scalaz._

trait ExamplePrimitive {
  self: ExampleMonad =>

  val defaultSize = 4

  val exampleLong: Example[Long] = Example[Long](identity)

  val exampleInt: Example[Int] = Example(_.toInt)

  def exampleInt(until: Int): Example[Int] = exampleInt.map(math.abs).map(_ % until)

  val exampleBoolean: Example[Boolean] = Example(_ > 0)

  val exampleByte: Example[Byte] = Example(_.toByte)

  val exampleDouble: Example[Double] =
    for {
      wholePart <- exampleLong
      fractionalPart <- exampleLong
    } yield wholePart.toDouble + (fractionalPart.toDouble / Long.MaxValue.toDouble)

  val exampleUnit: Example[Unit] = Example(_ => ())

  def exampleLift[A](a: A): Example[A] = a.point[Example]
}
