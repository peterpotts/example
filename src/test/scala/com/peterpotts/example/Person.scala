package com.peterpotts.example

import java.util.UUID

case class Person(id: UUID, name: String, age: Int, email: Option[String], friends: Seq[String])