package com.company.logic

import com.company.domain.Result

import java.time.ZonedDateTime
import scala.util.Try
import com.typesafe.scalalogging._


object TreeParser extends LazyLogging {
  type Tree = Array[Array[Int]]

  val emptyResult = Result(Nil, 0)

  def getShortestPath(tree: Tree): Result = {
    val resultBuffer = Array.fill[Result](tree.length)(emptyResult)

    logger.info(s"Starting at ${ZonedDateTime.now()}")

    tree.indices.reverse.foreach { i =>
      val previousResultBuffer = resultBuffer.clone()
      logger.debug(s"Processing line ${i}")
      tree(i).indices.foreach { j =>
        val value = tree(i)(j)

        val leftChild = resultOrDefault(j, previousResultBuffer)
        val rightChild = resultOrDefault(j + 1, previousResultBuffer)

        resultBuffer(j) = if (leftChild.total < rightChild.total) {
          Result(leftChild.path.appended(value), leftChild.total + value)
        } else {
          Result(rightChild.path.appended(value), rightChild.total + value)
        }
      }
    }

    logger.info(s"Finished at ${ZonedDateTime.now()}")
    resultBuffer(0).copy(path = resultBuffer(0).path.reverse)
  }

  def resultOrDefault(idx: Int, results: Array[Result]) = Try {
    results(idx)
  }.getOrElse(emptyResult)
}
