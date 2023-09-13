package com.company.logic

import com.company.logic.TreeParser.Tree

import scala.io.Source
import scala.util.Using

object FileProps {
  val filename = "src/main/resources/default.txt"
  val separator = " "
}
object TreeReader {
  def getContentFromStdIn() = getContentFromSource(Source.stdin)

  def getContentFromFile() = getContentFromSource(Source.fromFile(FileProps.filename))

  def getContentFromString(str: String) = getContentFromSource(Source.fromString(str))

  def getContentFromSource(source: Source): Tree = {
    Using(source) { file =>
      file.getLines
        .takeWhile(_.nonEmpty)
        .filter(_.nonEmpty)
        .map(_
          .split(FileProps.separator)
          .filter(_.nonEmpty)
          .map(_.toInt))
        .toArray
    }.getOrElse(Array.empty)
  }
}
