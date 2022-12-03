package mmxxii.days

import mmxxii.entities.Paper
import mmxxii.entities.Rock
import mmxxii.entities.Scissors
import mmxxii.entities.Shape

class Day2 : Abstract2022("02", "Rock Paper Scissors") {
    override fun part1(input: List<String>): Int = input
        .normalize { it[1].toShape() to it[0].toShape() }
        .map(::runGame)
        .sum()

    override fun part2(input: List<String>): Int = input
        .normalize { it[1] to it[0].toShape() }
        .map { it.first toShapeAgainst it.second }
        .map(::runGame)
        .sum()

    private fun <R> List<String>.normalize(transform: (List<String>) -> Pair<R, Shape>) = asSequence()
        .map { it.split(" ") }
        .map(transform)

    private fun runGame(round: Pair<Shape, Shape>): Int =
        round.first.points + (round.first against round.second)

    private fun String.toShape() = when (this) {
        "A" -> Rock()
        "B" -> Paper()
        "C" -> Scissors()
        // For part 1 only
        "X" -> Rock()
        "Y" -> Paper()
        "Z" -> Scissors()
        else -> throw NoWhenBranchMatchedException(this)
    }

    private infix fun String.toShapeAgainst(other: Shape): Pair<Shape, Shape> = Shape.allShapes.first {
        when (this) {
            "X" -> it loseAgainst other
            "Y" -> it drawAgainst other
            "Z" -> it winAgainst other
            else -> throw NoWhenBranchMatchedException(this)
        }
    } to other
}

