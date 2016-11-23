package com.peterpotts.example

import scalaz._

trait Example extends
  ExampleMonad with
  ExamplePrimitive with
  ExampleComposite with
  ExampleTuple with
  ExampleCollection with
  ExampleSpecial {
  private[example] def nextLong(): Long

  object Interpreter extends (Exampler ~> Id.Id) {
    def apply[A](exampler: Exampler[A]): Id.Id[A] = exampler.generator(nextLong())
  }

  implicit class DecoratedExample[T](example: Example[T]) {
    def next() = example.foldMap(Interpreter)
  }

}