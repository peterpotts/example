package com.peterpotts.example

import scala.util.Random
import scalaz._

object RandomExample extends Example {
  val interpreter = new (Exampler ~> Id.Id) {
    def apply[A](exampler: Exampler[A]): Id.Id[A] = exampler.generator(Random.nextLong())
  }
}
