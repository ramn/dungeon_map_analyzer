package se.ramn.mapper

import java.nio.file.Paths


object Main extends App {
  val pathToYamlFile = args.toList.headOption.getOrElse(sys.env("MAP_FILE"))
  val pathToYamlMap = Paths.get(pathToYamlFile)

  val document = new YamlDocumentParser(pathToYamlMap)
  val graph = new MapGraph(document.map)
}
