package com.peterpotts.example

import java.security.SecureRandom

import scala.util.Random

object RandomExample extends RandomExample(Random)

object SecureRandomExample extends RandomExample(new SecureRandom)

case class RandomExample(random: Random) extends Example {
  private[example] def nextLong() = random.nextLong()
}
