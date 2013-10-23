package se.ramn.mapper

import collection.JavaConverters._
import java.nio.file.Path
import java.nio.file.Paths
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.util.LinkedHashMap
import java.util.ArrayList
//import java.util.{Map => JMap}

object Main extends App {
  val pathToYamlMap = Paths.get(args(0))

  val mapAnalyzer = new MapAnalyzer(pathToYamlMap)
  mapAnalyzer.print
}

class MapAnalyzer(pathToYamlMap: Path) {
  val mapInYaml = io.Source.fromFile(pathToYamlMap.toFile).mkString
  val yamlParser = new Yaml

  type Document = Map[String, Map[String, Any]]

  val document = loadDocument(yamlParser, mapInYaml)
  val resources = loadResources(document)
  val map = loadMap(document)

  def loadDocument(yamlParser: Yaml, rawYaml: String): Document =
    yamlParser.load(mapInYaml)
    .asInstanceOf[LinkedHashMap[String, LinkedHashMap[String, Any]]]
    .asScala
    .toMap
    .map { case (key, value) => key -> value.asScala.toMap }

  def loadResources(document: Document): Map[String, List[String]] = document("Resources")
    .asInstanceOf[Map[String, ArrayList[String]]]
    .map { case (key, value) => key -> value.asScala.toList }

  def loadMap(document: Document): Map[String, Map[String, String]] =
    document("Map")
    .asInstanceOf[Map[String, LinkedHashMap[String, String]]]
    .map { case (key, value) => key -> value.asScala.toMap }

  def print {
    println(
      //resources.head._2.getClass,
      //resources.getClass,
      resources,
      map("Round Room"),
      map("Round Room").getClass
    )
  }
}
