import sbt._

object Settings {
  val user = "peterpotts"
  val name = "example"
  val organization = "com." + user
  val version = "1.0.1"
  val scalaVersion = "2.11.8"
  val scalaMajorVersion = scalaVersion.split('.').take(2).mkString(".")

  object Versions {
    val scalaz = "7.2.7"
    val scalaTest = "2.2.6"
    val mockito = "1.10.19"
  }

  object Dependencies {
    val scalaCompiler = "org.scala-lang" % "scala-compiler" % scalaVersion
    val scalaz = "org.scalaz" %% "scalaz-core" % Versions.scalaz
    val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest
    val mockitoCore = "org.mockito" % "mockito-core" % Versions.mockito
  }

}
