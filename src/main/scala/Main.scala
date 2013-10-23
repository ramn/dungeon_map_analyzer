package se.ramn.mapper

import java.nio.file.Paths


object Main extends App {
  val pathToYamlMap = Paths.get(args(0))

  val document = new YamlDocumentParser(pathToYamlMap)
  //document.print

  val graph = new MapGraph(document.map)
}


import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._
import scalax.collection.GraphEdge._

class MapGraph(roomsWithExits: Map[String, Map[String, String]]) {
  protected val allPairs: Iterable[LDiEdge[String]] = roomsWithExits
    .flatMap { case (sourceRoom, exits) =>
      exits
        .filter { case (exit, destRoom) =>
          sourceRoom != null && destRoom != null && exit != null
        }
        .map { case (exit, destRoom) =>
          //LDiEdge(sourceRoom, destRoom)(exit)
          LDiEdge(sourceRoom.toLowerCase, destRoom.toLowerCase)(exit)
        }
    }

  val graph = allPairs.foldLeft(Graph.empty[String, LDiEdge])(_ + _)

  //println(graph)
  //println
  //println(graph.edges)

  def n(node: String) = graph get node

  val pathOpt = n("damp cellar") shortestPathTo n("round room")

  def formatEdge(edge: MapGraph.this.graph.EdgeT): String =
    "%s '%s %s".format(edge._1, edge.label, edge._2)

  pathOpt.map(_.edges.map(formatEdge)).foreach(println)
}
