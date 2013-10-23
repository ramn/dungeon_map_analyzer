package se.ramn.mapper

import java.nio.file.Paths


object Main extends App {
  val pathToYamlMap = Paths.get(args(0))

  val document = new YamlDocumentParser(pathToYamlMap)
  document.print
}
