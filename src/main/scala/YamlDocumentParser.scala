package se.ramn.mapper

import collection.JavaConverters._
import java.nio.file.Path
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.util.LinkedHashMap
import java.util.ArrayList


class YamlDocumentParser(pathToYamlMap: Path) {
  protected val mapInYaml = io.Source.fromFile(pathToYamlMap.toFile).mkString
  protected val yamlParser = new Yaml

  type Document = Map[String, Map[String, Any]]

  lazy val document = loadDocument(yamlParser, mapInYaml)
  lazy val resources: Map[String, List[String]] = loadResources(document)
  lazy val map: Map[String, Map[String, String]] = loadMap(document)

  protected def loadDocument(yamlParser: Yaml, rawYaml: String): Document =
    yamlParser.load(mapInYaml)
    .asInstanceOf[LinkedHashMap[String, LinkedHashMap[String, Any]]]
    .asScala
    .toMap
    .map { case (key, value) => key -> value.asScala.toMap }

  protected def loadResources(document: Document) = document("Resources")
    .asInstanceOf[Map[String, ArrayList[String]]]
    .map { case (key, value) => key -> value.asScala.toList }

  protected def loadMap(document: Document) =
    document("Map")
    .asInstanceOf[Map[String, LinkedHashMap[String, String]]]
    .map { case (key, value) => key -> value.asScala.toMap }

  def print {
    println(resources)
    println
    println(map)
  }
}
