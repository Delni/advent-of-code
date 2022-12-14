package mmxxii.days

import java.awt.Point
import kotlin.math.sign

class Day14 : Abstract2022<Int>("14", "Regolith Reservoir") {
    override fun part1(input: List<String>): Int = input
        .map(String::toPath)
        .let { paths ->
            val grid = mutableSetOf<Point>()
            grid.addPaths(paths)
            val bounds = Point(
                grid.minOf { it.x }.toInt(),
                0,
            ) to Point(
                grid.maxOf { it.x }.toInt(),
                grid.maxOf { it.y }.toInt(),
            )
            var grain = Point(500, 0)
            var grains = 0
            while (grain inside bounds) {
                val formerPosition = Point(grain.x, grain.y)
                grain.moveOn(grid, floor = Int.MAX_VALUE)
                if (formerPosition == grain) {
                    grid.add(grain)
                    grain = Point(500, 0)
                    grains++
                }
            }
            grains
        }

    override fun part2(input: List<String>): Int = input
        .map(String::toPath)
        .let { paths ->
            val grid = mutableSetOf<Point>()
            grid.addPaths(paths)
            val floor = grid.maxOf { it.y }.toInt() + 2

            var grain = Point(500, 0)
            var grains = 0
            while (true) {
                val formerPosition = Point(grain.x, grain.y)
                grain.moveOn(grid, floor = floor)
                if (formerPosition == grain) {
                    grains++
                    grid.add(grain)
                    if (grain == Point(500, 0)) {
                        break
                    } else {
                        grain = Point(500, 0)
                    }
                }

            }
            grains
        }

}

infix fun Point.inside(bounds: Pair<Point, Point>) = (bounds.first.x <= x && x <= bounds.second.x)
        && (bounds.first.y <= y && y <= bounds.second.y)

fun Point.moveOn(grid: Iterable<Point>, floor: Int) {
    val down = Point(x, y + 1)
    val left = Point(x - 1, y + 1)
    val right = Point(x + 1, y + 1)
    when {
        !grid.contains(down) && y + 1 < floor -> move(x, y + 1)
        !grid.contains(left) && y + 1 < floor -> move(x - 1, y + 1)
        !grid.contains(right) && y + 1 < floor -> move(x + 1, y + 1)
    }
}

fun MutableSet<Point>.addPaths(paths: List<List<Pair<Point, Point>>>) = paths.forEach { path ->
    path.forEach {
        add(it.first)
        var currentPoint = it.first
        while (currentPoint != it.second) {
            add(currentPoint)
            currentPoint = Point(
                currentPoint.x + (it.second.x - it.first.x).sign,
                currentPoint.y + (it.second.y - it.first.y).sign
            )
        }
        add(it.second)
    }
}

fun String.toPath(): List<Pair<Point, Point>> = split("->")
    .windowed(2)
    .map {
        it.first().toPoint() to it.last().toPoint()
    }

fun String.toPoint(): Point = split(',')
    .map(String::trim)
    .map(String::toInt)
    .run { Point(first(), last()) }