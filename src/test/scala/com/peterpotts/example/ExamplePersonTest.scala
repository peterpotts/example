package com.peterpotts.example

import java.util.UUID

import com.peterpotts.example.TrivialExample._
import org.scalatest.{Matchers, WordSpec}

class ExamplePersonTest extends WordSpec with Matchers {
  "A trivial example person" should {
    "be trivial" in {
      val examplePerson =
        for {
          id <- exampleUUID
          name <- exampleString(8).map("Mr. " + _)
          age <- exampleInt(50)
          email <- exampleOption(exampleString)
          friends <- exampleList(exampleString, 10)
        } yield Person(id, name, age, email, friends)

      val trivialPerson = Person(
        UUID.nameUUIDFromBytes(Array.fill(16)(0.toByte)),
        name = "Mr. " + "0" * 8,
        age = 0,
        email = None,
        friends = List.fill(10)("0000")
      )

      examplePerson.next() shouldEqual trivialPerson
    }
  }
}
