package com.peterpotts.example

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

trait ExampleComposite {
  self: ExampleMonad with ExamplePrimitive =>

  def exampleEither[A, B](exampleA: Example[A], exampleB: Example[B]): Example[Either[A, B]] =
    for {
      condition <- exampleBoolean
      a <- exampleA
      b <- exampleB
    } yield if (condition) Left(a) else Right(b)

  def exampleOption[A](exampleA: Example[A]): Example[Option[A]] =
    for {
      condition <- exampleBoolean
      a <- exampleA
    } yield if (condition) Some(a) else None

  def exampleTry[A](exampleA: Example[A]): Example[Try[A]] =
    for {
      condition <- exampleBoolean
      a <- exampleA
    } yield if (condition) Success(a) else Failure(new RuntimeException)

  def exampleFuture[A](exampleA: Example[A]): Example[Future[A]] =
    for {
      condition <- exampleBoolean
      a <- exampleA
    } yield if (condition) Future.successful(a) else Future.failed(new RuntimeException)
}
