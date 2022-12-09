package mmxxii.days

import java.awt.Point
import kotlin.math.sign
import kotlin.math.sqrt


class Day9 : Abstract2022<Int>("09", "Rope Bridge") {
    override fun part1(input: List<String>): Int {
        val visited = mutableSetOf<Point>()
        val head = Point(0, 0)
        val tail = Point(0, 0)
        val moveRegex = Regex("""(\w) (\d+)""")
        input.forEach {
            val direction = moveRegex.find(it)!!.groupValues[1]
            val distance = moveRegex.find(it)!!.groupValues[2].toInt()
            val dx = when (direction) {
                "R" -> 1
                "L" -> -1
                else -> 0
            }
            val dy = when (direction) {
                "U" -> -1
                "D" -> 1
                else -> 0
            }
            val currentHead = Point(head.x, head.y)
            while (head.x != currentHead.x + dx * distance || head.y != currentHead.y + dy * distance) {
                head.translate(dx, dy)
                if (head.distance(tail) > sqrt(2.0)) {
                    tail.translate((head.x - tail.x).sign, (head.y - tail.y).sign)
                }
                visited.add(Point(tail.x, tail.y))
            }
        }
        return visited.size
    }

    override fun part2(input: List<String>) : Int {
        val visited = mutableSetOf<Point>()
        val head = Point(0, 0)
        val rope = listOf(
            head,
            Point(0, 0),
            Point(0, 0),
            Point(0, 0),
            Point(0, 0),
            Point(0, 0),
            Point(0, 0),
            Point(0, 0),
            Point(0, 0),
            Point(0, 0),
        )
        val moveRegex = Regex("""(\w) (\d+)""")
        input.forEach {
            val direction = moveRegex.find(it)!!.groupValues[1]
            val distance = moveRegex.find(it)!!.groupValues[2].toInt()
            val dx = when (direction) {
                "R" -> 1
                "L" -> -1
                else -> 0
            }
            val dy = when (direction) {
                "U" -> -1
                "D" -> 1
                else -> 0
            }
            val currentHead = Point(head.x, head.y)
            while (head.x != currentHead.x + dx * distance || head.y != currentHead.y + dy * distance) {
                head.translate(dx, dy)
                rope.windowed(2).forEach { knots ->
                    val previousKnot = knots.first()
                    val knot = knots.last()
                    if (previousKnot.distance(knot) > sqrt(2.0)) {
                        knot.translate((previousKnot.x - knot.x).sign, (previousKnot.y - knot.y).sign)
                    }
                }
                with(rope.last()) {
                    visited.add(Point(x, y))

                }
            }
        }
        return visited.size
    }
}