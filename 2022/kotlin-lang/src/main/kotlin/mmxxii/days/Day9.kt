package mmxxii.days

import java.awt.Point
import kotlin.math.sign
import kotlin.math.sqrt


class Day9 : Abstract2022<Int>("09", "Rope Bridge") {
    override fun part1(input: List<String>) = makeRopeWithLength(2) movingAlongAndVisiting input

    override fun part2(input: List<String>) = makeRopeWithLength(10) movingAlongAndVisiting input

    private fun makeRopeWithLength(length: Int): List<Point> = List(length) { Point(0, 0) }

    private infix fun List<Point>.movingAlongAndVisiting(input: List<String>) = mutableSetOf<Point>().apply {
        input.forEach { string ->
            string.asMove { move ->
                moveAlong(move) { add(last().location) }
            }
        }
    }.size

    private fun <T> String.asMove(block: (Vector) -> T) {
        val moveRegex = Regex("""(\w) (\d+)""")
        val direction = moveRegex.find(this)!!.groupValues[1]
        val distance = moveRegex.find(this)!!.groupValues[2].toInt()
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
        block(Vector(dx, dy, distance))
    }

    private fun <T> List<Point>.moveAlong(vector: Vector, sideEffect: List<Point>.() -> T) {
        val head = first()
        val target = Point(head.x + vector.x, head.y + vector.y)
        while (head.x != target.x || (head.y != target.y)) {
            head.translate(vector.dx, vector.dy)

            windowed(2).forEach { knots ->
                val previousKnot = knots.first()
                val knot = knots.last()

                takeIf { previousKnot.distance(knot) > sqrt(2.0) }
                    ?.let { knot translateByOneToward previousKnot }
            }

            sideEffect()
        }
    }

    private infix fun Point.translateByOneToward(point: Point) = translate((point.x - x).sign, (point.y - y).sign)


    private data class Vector(val dx: Int, val dy: Int, private val norm: Int) {
        val x
            get() = dx * norm

        val y
            get() = dy * norm
    }
}