package com.peterpotts.example

import java.net.{DatagramSocket, InetAddress, ServerSocket}
import java.util.UUID

import scala.collection.immutable.IndexedSeq
import scala.concurrent.duration.{DAYS, FiniteDuration, HOURS, MINUTES, TimeUnit}
import scala.util.Try

trait ExampleSpecial {
  self: ExampleMonad with ExamplePrimitive with ExampleCollection =>

  val exampleBigInt: Example[BigInt] = exampleBigInt(defaultSize)

  def exampleBigInt(size: Int): Example[BigInt] = exampleBytes(size).map(BigInt(_))

  val exampleBigDecimal: Example[BigDecimal] = exampleDouble.map(BigDecimal(_))

  val exampleAlphanumeric: Example[Char] = examplePick(('0' to '9') ++ ('A' to 'Z') ++ ('a' to 'z'))

  val exampleString: Example[String] = exampleString(defaultSize)

  def exampleString(size: Int): Example[String] =
    exampleList(exampleAlphanumeric, size).map(_.mkString)

  val examplePort: Example[Int] = {
    trait Protocol {
      def setReuseAddress(on: Boolean)

      def close()
    }

    val tcp = new ServerSocket(_: Int) with Protocol
    val udp = new DatagramSocket(_: Int) with Protocol

    def free(port: Int): Boolean =
      Try {
        List(tcp(port), udp(port)).foreach { protocol =>
          try protocol.setReuseAddress(on = true) finally protocol.close()
        }
      }.isSuccess

    Example { seed =>
      val ports = 49152 to 65535
      val (firstRange, secondRange) = ports.splitAt(seed.toInt % ports.size)
      (secondRange ++ firstRange).find(free).get
    }
  }

  val exampleTimeUnit: Example[TimeUnit] = examplePick(IndexedSeq(DAYS, HOURS, MINUTES))

  val exampleFiniteDuration: Example[FiniteDuration] =
    for {
      length <- examplePick(1 to 9)
      unit <- exampleTimeUnit
    } yield FiniteDuration(length, unit)

  val exampleUUID: Example[UUID] = exampleBytes(16).map(UUID.nameUUIDFromBytes)

  val exampleInetAddress: Example[InetAddress] =
    exampleList(exampleInt(256), 4).map(_.mkString(".")).map(InetAddress.getByName)
}
