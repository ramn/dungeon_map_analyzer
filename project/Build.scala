import sbt._
import sbt.Keys._

object Build extends sbt.Build {
  lazy val root = Project(
    id = "dungeon_map_analyzer",
    base = file("."),
    settings = Defaults.defaultSettings ++ mySettings
  )

  lazy val mySettings = Seq(
    organization := "se.ramn",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.10.3",
    resolvers += "SonaType" at "https://oss.sonatype.org/content/groups/public",
    scalacOptions := Seq(
      "-feature",
      "-unchecked",
      "-deprecation",
      "-encoding",
      "utf8"),
    libraryDependencies ++= dependencies,
    initialCommands in console := """
      val mapper = new se.ramn.mapper.Main
      import mapper.graph.from
      import scala.language.reflectiveCalls
      println("from(\"damp cellar\") to(\"round room\")")
      """
  )

  lazy val dependencies = Seq(
    "org.yaml" % "snakeyaml" % "1.14-SNAPSHOT",
    "com.assembla.scala-incubator" % "graph-core_2.10" % "1.7.0",
    "org.scalatest" % "scalatest_2.10" % "1.9.2" % "test"
  )
}
