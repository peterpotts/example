Example
=======

Example is a monadic random data generator using Scalaz.

Description
-----------

In a typical application, there is a need to generator example data for unit tests. Usually, the example data is
arbitrary, simplistic and worse still it is usually brittle. For example, suppose that many tests rely on example
instances of some case class. If the case class is ever modified, then many tests may have to be modified, even
though this change should not have impacted them. This library helps to remove the need for magic values and
makes tests less brittle.

Usage
-----

Consider an example case class for a person entity:

    case class Person(
        id: UUID,
        name: String,
        age: Int,
        email: Option[String],
        friends: Seq[String])

An example person generator can be created like this:

    import com.peterpotts.RandomExample._

    val examplePerson =
      for {
        id <- exampleUUID
        name <- exampleString(8).map("Mr. " + _)
        age <- exampleInt(50)
        email <- exampleOption(exampleString)
        friends <- exampleList(exampleString, 10)
      } yield Person(id, name, age, email, friends)

An example person is generated using the next method:

    val person: Person = examplePerson.next()

