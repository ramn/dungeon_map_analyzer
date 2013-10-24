Description
===========

A tool helpful when exploring Dungeons in text adventure games, MUDs and
Roguelikes. Anything where you get lost..  You will provide the map, as a YAML
file. See `INPUT_FORMAT` for more info on the file format.

The tool provides a pathfinding DSL, where you can look up a path between two
rooms.


Usage
=====

    // Start the sbt console
    `MAP_FILE=../path/to/map.yaml sbt console`

    val mapper = new se.ramn.mapper.Main
    import mapper.graph.from
    import scala.language.reflectiveCalls

    // Perform a search. The path will be printed to stdout.
    from("damp cellar") to("round room")

