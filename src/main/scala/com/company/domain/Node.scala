package com.company.domain

//trait Point
//case class Leaf(value: Int) extends Point
case class Node(value: Int, var left: Option[Node], var right: Option[Node])


