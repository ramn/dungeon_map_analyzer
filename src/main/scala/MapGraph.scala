package se.ramn.mapper

import scala.language.implicitConversions
import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.GraphPredef._
import scalax.collection.GraphEdge._
import scala.language.reflectiveCalls


class MapGraph(roomsWithExits: Map[String, Map[String, String]]) {
  protected val allPairs: Iterable[LDiEdge[String]] = roomsWithExits
    .flatMap { case (sourceRoom, exits) =>
      exits
        .filter { case (exit, destRoom) =>
          //if (destRoom == null) { println(sourceRoom) }
          //if (exit == null) { println(sourceRoom) }
          sourceRoom != null && destRoom != null && exit != null }
        .map { case (exit, destRoom) =>
          LDiEdge(sourceRoom.toLowerCase, destRoom.toLowerCase)(exit) }
    }

  val graph = allPairs.foldLeft(Graph.empty[String, LDiEdge])(_ + _)

  def n(node: String) = graph get node

  protected def formatEdge(edge: MapGraph.this.graph.EdgeT): String =
    "%s '%s %s".format(edge._1, edge.label, edge._2)

  implicit class PathSource(source: String) {
    def to(target: String) = {
      val path = n(source.toLowerCase) shortestPathTo n(target.toLowerCase)
      val instructions = path.map(_.edges.map(formatEdge)).getOrElse(List("Path not found"))
      instructions foreach println
    }
  }

  /*
   * Use case:
   *
   * import mapper.graph.PathSource
   * "damp cellar" to "round room"  // => prints the path steps
   */
}
