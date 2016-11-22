package com.peterpotts.example

import scala.util.Random
import scalaz._

object RandomExample extends Example {

  object RandomExamplerInterpreter extends (Exampler ~> Id.Id) {
    def apply[A](exampler: Exampler[A]): Id.Id[A] = exampler.generator(Random.nextLong())
  }

  implicit class DecoratedExample[T](example: Example[T]) {
    def next() = example.foldMap(RandomExamplerInterpreter)
  }

}
