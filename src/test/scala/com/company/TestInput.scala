package com.company

import com.company.domain.Result

object TestInput {
val input1 =
  """|1
     |1 2
     |1 2 3
     |3 2 3 1""".stripMargin
  val result1 = Result(List(1,1,1,2), 5)


  val input2 =
  """|1
     |1 1
     |1 1 1
     |3 2 3 1""".stripMargin
  val result2 = Result(List(1,1,1,1), 4)


  val input3 =
  """|1
     |2 1
     |5 2 3
     |5 9 3 1""".stripMargin
  val result3 = Result(List(1,1,3,1), 6)


  val allValues = List(
    (input1, result1),
    (input2, result2),
    (input3, result3),
  )
}
