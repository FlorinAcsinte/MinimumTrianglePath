import sbt._

object Dependencies {
  lazy val munit = "org.scalameta" %% "munit" % "0.7.29"
  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5"
  lazy val logback = "ch.qos.logback" % "logback-classic" % "1.3.5"
  lazy val scalaConfig = "com.typesafe" % "config" % "1.4.2"
  lazy val scalaParallel = "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"
}
