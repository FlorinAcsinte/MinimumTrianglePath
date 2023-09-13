package com.company.logic

import com.company.domain.Result

import java.time.ZonedDateTime
import scala.util.Try
import com.typesafe.scalalogging._
import scala.collection.parallel.CollectionConverters._


object TreeParser extends LazyLogging {
  type Tree = Array[Array[Int]]

  private val emptyResult = Result(Nil, 0)

  def getShortestPath(tree: Tree): Result = {
    val resultBuffer = Array.fill[Result](tree.length)(emptyResult)

    logger.info(s"Starting at ${ZonedDateTime.now()}")

    tree.indices.reverse.foreach { i =>
      val previousResultBuffer = resultBuffer.clone()
      logger.debug(s"Processing line ${i}")
      tree(i).indices.par.foreach { j =>
        val value = tree(i)(j)

        val leftChild = resultOrDefault(j, previousResultBuffer)
        val rightChild = resultOrDefault(j + 1, previousResultBuffer)

        resultBuffer(j) = if (leftChild.total < rightChild.total) {
          resultFromChild(leftChild, value)
        } else {
          resultFromChild(rightChild, value)
        }
      }
    }

    logger.info(s"Finished at ${ZonedDateTime.now()}")
    resultBuffer(0).copy(path = resultBuffer(0).path.reverse)
  }

  private def resultFromChild(child: Result, newValue: Int): Result =
    Result(child.path.appended(newValue), child.total + newValue)

  private def resultOrDefault(idx: Int, results: Array[Result]) = Try {
    results(idx)
  }.getOrElse(emptyResult)
}
