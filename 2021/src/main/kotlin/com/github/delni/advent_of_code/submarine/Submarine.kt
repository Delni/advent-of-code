package com.github.delni.advent_of_code.submarine


data class Position(
    val horizontal: Int, val depth: Int, var aim: Int
)

data class Submarine(/* ... */) {
    infix fun moveTo(step: Int) {
        // cf exemple précédent
    }
}

fun Position.displayDeepBluePosition() =
    this.takeIf { it.depth > 1000 }
        ?.toString()
        ?: "Pas encore assez profond ($depth)!"


fun main() {
    val position = Position(0, 1000, 0)
    position.displayDeepBluePosition()
}
//data class Submarine(
//    private var position: Position = Position(0, 0, 0),
//    var epsilon: String = "",
//    var gamma: String = "",
//) {
//    infix fun moveTo(step: Pair<Direction, Int>) = when (step.first) {
//        Direction.FORWARD -> {
//            position = position.also { print("La position actuelle est $it") } // Ne modifie pas la valeur
//                .run {
//                    copy(
//                        horizontal = horizontal + step.second, depth = depth + step.second * aim
//                    ) // Retourne une valeur modifiée
//                }
//        }
//        Direction.DOWN -> position.aim += step.second
//        Direction.UP -> position.aim -= step.second
//    }
//
//    @Deprecated("Part 01 of Day 02")
//    infix fun moveSimplyTo(step: Pair<Direction, Int>) = when (step.first) {
//        Direction.FORWARD -> position.horizontal += step.second
//        Direction.DOWN -> position.depth += step.second
//        Direction.UP -> position.depth -= step.second
//    }
//
//    fun diagnose(diagnostic: List<String>) {
//        epsilon = ""
//        gamma = ""
//        val size = diagnostic[0].length
//        for (i in 1..size) {
//            val average = diagnostic.map { it.split("")[i] }.map(Integer::parseInt)
//                .fold(0.0) { acc, elem -> acc + elem } / diagnostic.size
//            epsilon += average.takeIf { it >= 0.5 }?.let { "0" } ?: "1"
//            gamma += average.takeIf { it > 0.5 }?.let { "1" } ?: "0"
//        }
//    }
//
//    val currentPosition: Int
//        get() = position.horizontal * position.depth
//}