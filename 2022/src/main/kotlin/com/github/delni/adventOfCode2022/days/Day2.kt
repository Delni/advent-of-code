package com.github.delni.adventOfCode2022.days

class Day2 : Abstract2022("02", "Rock Paper Scissors") {
    override fun part1(input: List<String>): Int = input
        .asSequence()
        .map { it.split(" ") }
        .map { Shape.parseString(it[0]) to Shape.parseString(it[1]) }
        .map(::runGame)
        .sum()

    override fun part2(input: List<String>): Int = input
        .asSequence()
        .map { it.split(" ") }
        .map { Shape.parseString(it[0]) to it[1] }
        .map { it.first to Shape.findOutput(it.second, it.first) }
        .map(::runGame)
        .sum()

    private fun runGame(round: Pair<Shape, Shape>): Int =
        round.second.points + when (round.second.compareTo(round.first)) {
            0 -> 3
            1 -> 6
            else -> 0
        }
}

sealed class Shape(val points: Int) : Comparable<Shape> {
    companion object {
        fun parseString(str: String) = when (str) {
            "A" -> Rock()
            "B" -> Paper()
            "C" -> Scissor()
            // For part 1 only
            "X" -> Rock()
            "Y" -> Paper()
            "Z" -> Scissor()
            else -> throw NoWhenBranchMatchedException(str)
        }
        fun findOutput(str: String, other: Shape) = listOf(Rock(), Paper(), Scissor()).first {
            when (str) {
                "X" ->  it < other
                "Y" -> it.compareTo(other) == 0
                "Z" ->  it > other
                else -> throw NoWhenBranchMatchedException(str)
            }
        }
    }
}

class Rock : Shape(1) {
    override fun compareTo(other: Shape) = when (other) {
        is Paper -> -1
        is Rock -> 0
        is Scissor -> 1
    }

}

class Paper : Shape(2) {
    override fun compareTo(other: Shape) = when (other) {
        is Paper -> 0
        is Rock -> 1
        is Scissor -> -1
    }

}

class Scissor : Shape(3) {
    override fun compareTo(other: Shape) = when (other) {
        is Paper -> 1
        is Rock -> -1
        is Scissor -> 0
    }

}