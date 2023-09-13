package com.company

import com.company.logic.{TreeParser, TreeReader}

class TreeParserSpec extends munit.FunSuite {
  test("correctly parse simple tree from string") {
    val tree = TestInput.input1


    val treeAsArray = TreeReader.getContentFromString(tree).map(_.toList).toList
    val expected = Array(Array(1), Array(1, 2), Array(1, 2, 3), Array(3, 2, 3, 1)).map(_.toList).toList
    assert(treeAsArray == expected)
  }

  test("correctly parse inputs") {
    TestInput.allValues.foreach { case (input, expected) =>
      val treeAsArray = TreeReader.getContentFromString(input)
      val result = TreeParser.getShortestPath(treeAsArray)
      println(result)
      println(expected)
      assert(result == expected)
    }
  }
}
