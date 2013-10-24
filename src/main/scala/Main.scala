package se.ramn.mapper

import java.nio.file.Paths
import scala.language.reflectiveCalls


object Main extends App {
  val pathToYamlFile = args.toList.headOption.getOrElse(sys.env("MAP_FILE"))
  val pathToYamlMap = Paths.get(pathToYamlFile)

  val document = new YamlDocumentParser(pathToYamlMap)
  val graph = new MapGraph(document.map)

  import graph.from
  from("damp cellar") to("round room")
}

class Main(yamlPathArgOpt: Option[String]) {
  def this() = this(None)

  val pathToYamlFile = yamlPathArgOpt.getOrElse(sys.env("MAP_FILE"))
  val pathToYamlMap = Paths.get(pathToYamlFile)

  val document = new YamlDocumentParser(pathToYamlMap)
  val graph = new MapGraph(document.map)

  println(
    """|val mapper = new se.ramn.mapper.Main
       |import mapper.graph.from
       |import scala.language.reflectiveCalls
       |from("damp cellar") to("round room")""".stripMargin)
}
