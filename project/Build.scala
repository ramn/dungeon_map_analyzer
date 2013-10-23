import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtStartScript

object Build extends sbt.Build {
  lazy val root = Project(
    id = "dungeon_map_analyzer",
    base = file("."),
    settings = Defaults.defaultSettings
      ++ mySettings
      ++ SbtStartScript.startScriptForClassesSettings
  )

  lazy val mySettings = Seq(
    mainClass in Compile := None, // forces us to use explicit class with target/start
    organization := "se.ramn",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.10.3",
    resolvers += "SonaType" at "https://oss.sonatype.org/content/groups/public",
    scalacOptions := Seq(
      "-unchecked",
      "-deprecation",
      "-encoding",
      "utf8"),
    libraryDependencies ++= dependencies
  )

  lazy val dependencies = Seq(
    "org.yaml" % "snakeyaml" % "1.14-SNAPSHOT",
    "org.scalatest" % "scalatest_2.10" % "1.9.2" % "test"
  )
}
