package com.company.logic

import scala.io.Source
import scala.util.Using

object FileProps {
  val filename = "src/main/resources/1.txt"
  val separator = " "
}
object TreeReader {
  def getContentFromStdIn() = getContentFromSource(Source.stdin)

  def getContentFromFile() = getContentFromSource(Source.fromFile(FileProps.filename))

  def getContentFromSource(source: Source): Array[Array[Int]] = {
    Using(source) { file =>
      file.getLines
        .takeWhile(_.nonEmpty)
        .map(_
          .split(FileProps.separator)
          .filter(_.nonEmpty)
          .map(_.toInt))
        .toArray
    }.getOrElse(Array.empty)
  }
}
