package mmxxii.days

import mmxxii.entities.HillClimbing
import mmxxii.entities.findCoordsIn
import java.awt.Point

class Day12 : Abstract2022<Int>("12", "Hill Climbing Algorithm") {
    override fun part1(input: List<String>): Int = HillClimbing('S' findCoordsIn input)
        .apply { breadthSearchFirst(input) }
        .path
        .size

    override fun part2(input: List<String>): Int = input.flatMapIndexed { x, row ->
        row.mapIndexedNotNull { y, char ->
            char
                .takeIf { it == 'S' || it == 'a' }
                ?.let { HillClimbing(Point(x, y)).apply { breadthSearchFirst(input) } }
                ?.path
        }
    }
        .filter(MutableSet<Point>::isNotEmpty)
        .minOf(MutableSet<Point>::size)
}