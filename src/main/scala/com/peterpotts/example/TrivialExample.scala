package com.peterpotts.example

import scalaz._

object TrivialExample extends Example {
  val interpreter = new (Exampler ~> Id.Id) {
    def apply[A](exampler: Exampler[A]): Id.Id[A] = exampler.generator(0L)
  }
}
