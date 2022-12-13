package mmxxii.entities

import java.awt.Point

class HillClimbing(private val start: Point) {
    val path = mutableSetOf<Point>()

    fun breadthSearchFirst(input: List<String>) {
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
                        .takeUnless { it in reached }
                        ?.let { p ->
                            frontier.add(it)
                            reached[it] = current
                        }
                }
        }

        // Follow path
        var current: Point? = end

        while (current != start && reached[end] != null) {
            current?.let { path.add(it) }
            current = reached[current]
        }
    }
}

infix fun Point.asRawValueFrom(input: List<String>) = input[x][y]


infix fun Point.asValueFrom(input: List<String>) = asRawValueFrom(input).let {
    when (it) {
        'E' -> 'z'
        'S' -> 'a'
        else -> it
    }
}

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