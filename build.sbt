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
    s"scm:git:https://github.com/${Settings.user}/${Settings.name}.git",
    Some(s"scm:git:git@github.com:${Settings.user}/${Settings.name}.git")))

libraryDependencies ++= Seq(scalaCompiler, scalaz, scalaTest % "test", mockitoCore % "test")
