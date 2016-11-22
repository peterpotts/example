package com.peterpotts.example

import com.peterpotts.example.RandomExample._
import org.scalatest.{Matchers, WordSpec}

class ExamplePersonTest extends WordSpec with Matchers {
  "A random example person" should {
    "be uniform" in {
      val examplePerson =
        for {
          id <- exampleUUID
          name <- exampleString(8).map("Mr. " + _)
          age <- exampleInt(50)
          email <- exampleOption(exampleString)
          friends <- exampleList(exampleString, 10)
        } yield Person(id, name, age, email, friends)

      examplePerson.next()
    }
  }
}
