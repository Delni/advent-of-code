package days

import AbstractDay
import entities.Grid2D
import entities.Guard
import entities.Point2D

class Day6 : AbstractDay<Int>("06", 2024, "Guard Gallivant") {
    override fun part1(input: List<String>) = with(Grid2D.from(input)) {
        var visited = mutableSetOf<Point2D>()
        var guard: Guard = Guard(Point2D(0, 0))
        forEachPoint { value, point ->
            value.takeIf { it == "^" }?.let { string -> guard = Guard(origin = point) }
        }

        while (guard inside this) {
            visited.add(guard.position)
            guard.moveOn(this)
        }

        visited.size
    }

    override fun part2(input: List<String>) = input.size
}

