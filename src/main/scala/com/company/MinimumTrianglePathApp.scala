package com.company

import com.company.domain.Node

import java.time.ZonedDateTime
import scala.util.Try

object FileProps {
  val filename = "src/main/resources/data_big.txt"
  val separator = " "
}
object MinimumTrianglePathApp extends App {

  import scala.io.Source

  var startPoint: Option[Node] = None; //TODO: Any Other Way?
  var lastReadLine: List[Node] = Nil
  val input = Source.fromFile(FileProps.filename)
    .getLines
    .filter(_.nonEmpty)
    .map(_
      .split(FileProps.separator)
      .filter(_.nonEmpty)
      .map(_.toInt))
    .toArray

  //print all
//  (0 until input.length).foreach { i =>
//    (0 until input(i).length).foreach { j =>
//      val element = input(i)(j)
//      print(element)
//      print(" ")
//    }
//    println("")
//  }

//  var bestTotal: Int = Int.MaxValue //TODO: Get rid of val
//  var bestPath: List[Int] = Nil //TODO: Get rid of val
//
//  //parse
////  {
////    def tryAll(i: Int, j: Int, path: List[Int], total: Int): Unit = {
////      println(s"trying indexes $i and $j. value is ${input(i)(j)}")
////      if (input.isDefinedAt(i + 1)) {
////        tryAll(i + 1, j, path.appended(input(i + 1)(j)), total + input(i + 1)(j))
////        tryAll(i + 1, j + 1, path.appended(input(i + 1)(j + 1)), total + input(i + 1)(j + 1))
////      } else {
////        if (total < bestTotal) {
////          bestTotal = total
////          bestPath = path
////        }
////      }
////    }
////
////    println("Starting...")
////    tryAll(0, 0, Nil, input(0)(0))
////
////    println("Done...")
////    println(bestTotal)
////    println(bestPath.mkString(" "))
///
  ///  }


  case class Result(path: List[Int], total: Int)

  val emptyResult = Result(Nil, 0)
  val resultBuffer = Array.fill[Result](input.length)(emptyResult)

  println(s"Starting at ${ZonedDateTime.now()}")
  (0 until input.length).reverse.foreach { i =>
    val previousResultBuffer = resultBuffer.clone()
//    println(s"Procesing ling ${i}")
    (0 until input(i).length).foreach { j =>
      val value = input(i)(j)

//      println(s"Doing stuff from $i and $j")
      //Try because of outOfBounds style errors
      val leftResult = Try {
        previousResultBuffer(j)
      }.getOrElse(emptyResult)

      val rightResult = Try {
        previousResultBuffer(j + 1)
      }.getOrElse(emptyResult)

      resultBuffer(j) = if (leftResult.total < rightResult.total) {
        Result(leftResult.path.appended(value), leftResult.total + value)
      } else {
        Result(rightResult.path.appended(value), rightResult.total + value)
      }
    }
  }

  val result = resultBuffer(0)
  println(s"Total is ${result.total}. \nPath taken: ${result.path.reverse}")
  println(s"Finished at ${ZonedDateTime.now()}")






  def printAll(input: Array[Array[Result]]) =
    println(
      input
        .map { line =>line.mkString(" ")}
        .mkString("\n")
    )
}

