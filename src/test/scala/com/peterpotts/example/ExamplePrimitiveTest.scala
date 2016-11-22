package com.peterpotts.example

import com.peterpotts.example.RandomExample._
import org.scalatest.{Matchers, WordSpec}

class ExamplePrimitiveTest extends WordSpec with Matchers {
  "A random example boolean" should {
    "be uniform" in {
      exampleList(exampleBoolean, 1000).next().count(identity) should be > 450
    }
  }
}
