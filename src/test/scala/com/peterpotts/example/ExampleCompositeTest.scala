package com.peterpotts.example

import com.peterpotts.example.TrivialExample._
import org.scalatest.{Matchers, WordSpec}

import scala.util.Success

class ExampleCompositeTest extends WordSpec with Matchers {
  "A trivial example either" should {
    "be trivial" in {
      exampleEither(exampleLong, exampleString).next() shouldEqual Right("0" * defaultSize)
    }
  }

  "A trivial example option" should {
    "be trivial" in {
      exampleOption(exampleInt).next() shouldEqual Some(0)
    }
  }

  "A trivial example try" should {
    "be trivial" in {
      exampleTry(exampleBoolean).next() shouldEqual Success(false)
    }
  }

  "A trivial example future" should {
    "be trivial" in {
      exampleFuture(exampleDouble).next().value shouldEqual Some(Success(0.0))
    }
  }
}