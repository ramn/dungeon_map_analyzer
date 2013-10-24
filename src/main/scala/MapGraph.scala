package se.ramn.mapper

import scalax.collection.Graph
import scalax.collection.edge.LDiEdge
import scalax.collection.edge.Path
import scalax.collection.GraphPredef._
import scalax.collection.GraphEdge._


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

  val pathOpt = n("damp cellar") shortestPathTo n("round room")

  pathOpt.map(_.edges.map(formatEdge)).foreach(println)

  def from(source: String) = new {
    def to(target: String): Path = {
      n(source) shortestPathTo n(target)
    }
  }

  from("damp cellar") to("round room")
}
