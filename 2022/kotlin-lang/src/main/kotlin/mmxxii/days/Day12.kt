package mmxxii.days

import java.awt.Point

class Day12 : Abstract2022<Int>("12", "Hill Climbing Algorithm") {
    override fun part1(input: List<String>): Int = HillClimbingStar('S' findCoordsIn input)
        .apply { aStar(input) }
        .path
        .size

    override fun part2(input: List<String>): Int = input.flatMapIndexed { x, row ->
        row.mapIndexedNotNull { y, char ->
            char
                        .takeIf { it == 'S' || it == 'a' }
                        ?.let { HillClimbingStar(Point(x,y)).apply { aStar(input) } }
                        ?.path
                        ?.size
        }
    }.min()
}

class HillClimbingStar(private val start: Point) {
    val path = mutableSetOf<Point>()

fun aStar(input: List<String>) {
    path.removeAll(path)
    val end = 'E' findCoordsIn input
    val frontier = mutableListOf(start)
    val reached = mutableMapOf<Point, Point?>(start to null)
    val boundaries = Boundary(
        topLeft = Point(0, 0),
        bottomRight = Point(input.size - 1, input.first().length - 1)
    )

    // Make paths
    while (frontier.isNotEmpty()) {
        val current = frontier.removeAt(0)
        if ((current asRawValueFrom input) == 'E') {
            break
        }
        (current getNeighborsIn boundaries)
            .filter { (it asValueFrom input).code <= (current asValueFrom input).code + 1 }
            .forEach {
                it
                    .takeUnless { it in reached.keys }
                    ?.let { p ->
                        frontier.add(it)
                        reached[it] = current
                    }
            }
    }

    // Follow path
    var current: Point? = end
    while (current != start) {
        current?.let { path.add(it) }
        current = reached[current]
    }
}
}

infix fun Point.asValueFrom(input: List<String>) = asRawValueFrom(input).let {
    when (it) {
        'E' -> 'z'
        'S' -> 'a'
        else -> it
    }
}

infix fun Point.asRawValueFrom(input: List<String>) = input[x][y]

infix fun Point.getNeighborsIn(boundaries: Boundary): List<Point> = listOfNotNull(
    takeIf { x > boundaries.topLeft.x }?.let { Point(x - 1, y) },
    takeIf { x < boundaries.bottomRight.x }?.let { Point(x + 1, y) },
    takeIf { y > boundaries.topLeft.y }?.let { Point(x, y - 1) },
    takeIf { y < boundaries.bottomRight.y }?.let { Point(x, y + 1) },
)

infix fun Char.findCoordsIn(input: List<String>): Point {
    val row = input.indexOfFirst { col -> this in col }
    val col = input[row].indexOfFirst { it == this }
    return Point(row, col)
}

data class Boundary(
    val topLeft: Point,
    val bottomRight: Point,
)