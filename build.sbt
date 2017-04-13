import Settings.Dependencies._
import sbt.Keys._

name := Settings.name
organization := Settings.organization
version := Settings.version
homepage := Some(url(s"https://github.com/${Settings.user}/${Settings.name}"))
startYear := Some(2016)
scalaVersion := Settings.scalaVersion

scmInfo := Some(
  ScmInfo(
    url(s"https://github.com/${Settings.user}/${Settings.name}"),
    s"scm:git@github.com:${Settings.user}/${Settings.name}.git"
  )
)

licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.html"))

developers := List(
  Developer(
    id    = Settings.name,
    name  = "Peter Potts",
    email = "peter.potts@gmail.com",
    url   = url("http://peterpotts.com")
  )
)

pomIncludeRepository := { _ => false }
publishMavenStyle := true
publishArtifact in Test := false

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

libraryDependencies ++= Seq(scalaCompiler, scalaz, scalaTest % "test", mockitoCore % "test")
