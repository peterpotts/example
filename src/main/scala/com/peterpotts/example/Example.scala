package com.peterpotts.example

import scalaz._

trait Example extends
  ExampleMonad with
  ExamplePrimitive with
  ExampleComposite with
  ExampleTuple with
  ExampleCollection with
  ExampleSpecial {
  val interpreter: Exampler ~> Id.Id

  implicit class DecoratedExample[T](example: Example[T]) {
    def next() = example.foldMap(interpreter)
  }

}