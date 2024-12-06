package days

import AbstractDay
import kotlin.math.max
import kotlin.math.min

class Day4 : AbstractDay<Int>("04", 2024, "Ceres Search") {
    override fun part1(input: List<String>) = Grid2D.from(input).let { grid ->
        var total = 0
        grid.forEachPoint { string, point ->
            if (string == "X") {
                val xmasFound = grid.getNeighbours(point).filter { neighbour ->
                    grid getValueOf neighbour == "M"
                }.filter {
                    val vector = Grid2D.Vector2D(it.x - point.x, it.y - point.y)
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
                    grid getValueOf (point + Grid2D.Vector2D(-1, -1)),
                    grid getValueOf point,
                    grid getValueOf point + Grid2D.Vector2D(1, 1)
                ).joinToString("")

                val crossRight = listOf(
                    grid getValueOf (point + Grid2D.Vector2D(1, -1)),
                    grid getValueOf point,
                    grid getValueOf (point + Grid2D.Vector2D(-1, 1))
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

class Grid2D(val grid: List<List<String>>) {
    class Point2D(val x: Int, val y: Int) {
        operator fun plus(other: Vector2D) = Point2D(x + other.x, y + other.y)

        infix fun inside(grid: Grid2D) = x in 0..grid.columnCount - 1 && y in 0..grid.rowsCount - 1
    }

    class Vector2D(val x: Int, val y: Int)

    val rowsCount = grid.size
    val columnCount = grid[0].size

    fun getNeighbours(point: Point2D): List<Point2D> {
        val neighbours = mutableListOf<Point2D>()
        for (y: Int in max(point.y - 1, 0)..min(grid.size - 1, point.y + 1)) {
            for (x: Int in max(point.x - 1, 0)..min(grid[y].size - 1, point.x + 1)) {
                neighbours.add(Point2D(x, y))
            }
        }
        return neighbours
    }

    fun forEachPoint(transform: (String, Point2D) -> Unit) {
        grid.forEachIndexed { y, line ->
            line.forEachIndexed { x, column -> transform(column, Point2D(x, y)) }
        }
    }

    infix fun getValueOf(point: Point2D) = point.takeIf { it inside this }?.run { grid[y][x] }
    fun findWord(word: String, origin: Point2D, direction: Vector2D): Boolean = origin
        .takeIf { it inside this }
        ?.takeIf { getValueOf(it) == word[0].toString() }
        ?.let {
            val nextWord = word.substring(1)
            if (nextWord.isEmpty()) {
                true
            } else {
                findWord(nextWord, origin + direction, direction)
            }
        } == true

    companion object {
        fun from(input: List<String>) = Grid2D(input.map { it.split("").filter(String::isNotEmpty) })
    }
}