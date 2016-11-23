package com.peterpotts.example

import scala.util.Random

object RandomExample extends Example {
  private[example] def nextLong() = Random.nextLong()
}
