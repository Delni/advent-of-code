package mmxxi.submarine


data class Position(
    var horizontal: Int, var depth: Int, var aim: Int
)

class Submarine {
    private val position = Position(0, 0, 0)
    var epsilon: String = ""
    var gamma: String = ""

    infix fun moveTo(step: Pair<Direction, Int>) = when (step.first) {
        Direction.FORWARD -> {
            position.horizontal += step.second
            position.depth += step.second * position.aim
        }
        Direction.DOWN -> position.aim += step.second
        Direction.UP -> position.aim -= step.second
    }

    @Deprecated("Part 01 of Day 02")
    infix fun moveSimplyTo(step: Pair<Direction, Int>) = when (step.first) {
        Direction.FORWARD -> position.horizontal += step.second
        Direction.DOWN -> position.depth += step.second
        Direction.UP -> position.depth -= step.second
    }

    fun diagnose(diagnostic: List<String>) {
        epsilon = ""
        gamma = ""
        val size = diagnostic[0].length
        for (i in 1..size) {
            val average = diagnostic
                .map { it.split("")[i] }
                .map(Integer::parseInt)
                .fold( 0.0  ) { acc, elem -> acc+elem } / diagnostic.size
            epsilon += average.takeIf { it >= 0.5 }?.let { "0" } ?: "1"
            gamma += average.takeIf { it > 0.5 }?.let { "1" } ?: "0"
        }
    }

    val currentPosition: Int
        get() = position.horizontal * position.depth
}