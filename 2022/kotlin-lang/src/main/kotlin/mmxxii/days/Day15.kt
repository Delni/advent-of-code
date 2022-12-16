package mmxxii.days

import java.awt.Point
import kotlin.math.absoluteValue

class Day15(val offset: Int = 2000000) : Abstract2022<Int>("15", "Beacon Exclusion Zone") {
    override fun part1(input: List<String>): Int = input
        .flatMap(String::toSensorsAndBeacons)
        .map(Pair<Sensor, Beacon>::first)
        .map { it.cover(offset) }
        .flatMap { it.asSequence() }
        //.sortedBy(Point::x)
        .toSet()
        .size - 1

    override fun part2(input: List<String>): Int = input.size
}

data class Beacon(val position: Point)
data class Sensor(val position: Point, val closestBeaconDistance: Int) {
    private fun coverageAt(line: Int): Int = (closestBeaconDistance - (position.y - line).absoluteValue)
        .takeIf { it > 0 }
        ?.let { it * 2 + 1}
        ?: 0

    fun cover(line: Int) = coverageAt(line).let { (position.x - it /2 ) .. (position.x + it /2) }
}

fun String.toSensorsAndBeacons(): List<Pair<Sensor, Beacon>> =
    Regex("""Sensor at x=(.+), y=(.+): closest beacon is at x=(.+), y=(.+)""")
        .findAll(this)
        .map {
            it.groupValues.drop(1)
        }.map {
            val point1 = Point(it[0].toInt(), it[1].toInt())
            val point2 = Point(it[2].toInt(), it[3].toInt())
            Sensor(
                position = point1,
                closestBeaconDistance = point1 manhattanDistanceTo point2
            ) to Beacon(point2)
        }.toList()

infix fun Point.manhattanDistanceTo(other: Point) = (x - other.x).absoluteValue + (y - other.y).absoluteValue