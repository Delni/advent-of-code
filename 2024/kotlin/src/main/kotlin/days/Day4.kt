package days

import AbstractDay
import entities.Grid2D
import entities.Vector2D

class Day4 : AbstractDay<Int>("04", 2024, "Ceres Search") {
    override fun part1(input: List<String>) = Grid2D.from(input).let { grid ->
        var total = 0
        grid.forEachPoint { string, point ->
            if (string == "X") {
                val xmasFound = grid.getNeighbours(point).filter { neighbour ->
                    grid getValueOf neighbour == "M"
                }.filter {
                    val vector = Vector2D(it.x - point.x, it.y - point.y)
                    grid.findWord("XMAS", point, vector)
                }.size

                total += xmasFound
            }
        }
        total
    }

    override fun part2(input: List<String>) = Grid2D.from(input).let { grid ->
        var total = 0
        grid.forEachPoint { string, point ->
            if (string == "A") {
                val crossLeft = listOf(
                    grid getValueOf (point + Vector2D(-1, -1)),
                    grid getValueOf point,
                    grid getValueOf point + Vector2D(1, 1)
                ).joinToString("")

                val crossRight = listOf(
                    grid getValueOf (point + Vector2D(1, -1)),
                    grid getValueOf point,
                    grid getValueOf (point + Vector2D(-1, 1))
                ).joinToString("")

                if ("MAS" in listOf(crossLeft, crossLeft.reversed()) && "MAS" in listOf(
                        crossRight,
                        crossRight.reversed()
                    )
                ) {
                    total++
                }

            }
        }
        total
    }
}

