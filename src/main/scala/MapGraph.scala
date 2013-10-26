package se.ramn.mapper

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
          sourceRoom != null && destRoom != null && exit != null }
        .map { case (exit, destRoom) =>
          LDiEdge(sourceRoom.toLowerCase, destRoom.toLowerCase)(exit) }
    }

  val graph = allPairs.foldLeft(Graph.empty[String, LDiEdge])(_ + _)

  def n(node: String) = graph get node

  protected def formatEdge(edge: MapGraph.this.graph.EdgeT): String =
    "%s '%s %s".format(edge._1, edge.label, edge._2)

  def from(source: String) = new {
    def to(target: String) = {
      val sourceNode = n(source.toLowerCase)
      val targetNode = n(target.toLowerCase)

      // Due to a bug in shortestPathTo where it will not always find an
      // existing path, we fall back to use pathTo.
      val path =
        (sourceNode shortestPathTo targetNode) orElse
        (sourceNode pathTo targetNode)

      val instructions = path.map(_.edges.map(formatEdge)).getOrElse(List.empty)
      instructions foreach println
    }
  }

  /*
   * Use case:
   *
   * import graph.from
   * from("damp cellar") to("round room")
   */
}
