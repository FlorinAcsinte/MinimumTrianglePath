package com.company

import com.company.domain.Result
import com.company.logic.TreeParser.Tree
import com.company.logic.{TreeParser, TreeReader}
import com.typesafe.scalalogging._


object MinimumTrianglePathApp extends App with LazyLogging {
  val emptyResult = Result(Nil, 0)

  val input: Tree = TreeReader.getContentFromFile()
//  val input: Tree = TreeReader.getContentFromStdIn()

  val result = TreeParser.getShortestPath(input)
  println(s"Total is ${result.total}. \nPath taken: ${result.path}")
}

