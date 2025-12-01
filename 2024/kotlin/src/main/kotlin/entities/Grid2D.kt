package entities

import kotlin.math.max
import kotlin.math.min


data class Point2D(val x: Int, val y: Int) {
    operator fun plus(other: Vector2D) = Point2D(x + other.x, y + other.y)
    infix fun inside(grid: Grid2D) = x in 0..grid.columnCount - 1 && y in 0..grid.rowsCount - 1

}

data class Vector2D(val x: Int, val y: Int)

data class Grid2D(val grid: List<List<String>>) {


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


    fun print(transform: (point: Point2D) -> String) {
        grid.forEachIndexed { y, line ->
            println(line.mapIndexed { x, column -> transform(Point2D(x, y)) }.joinToString(""))
        }
    }

    companion object {
        fun from(input: List<String>) = Grid2D(input.map { it.split("").filter(String::isNotEmpty) })
    }
}