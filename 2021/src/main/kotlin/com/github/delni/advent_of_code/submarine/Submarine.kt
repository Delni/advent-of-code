package com.github.delni.advent_of_code.submarine


data class Position(
    var horizontal: Int,
    var depth: Int,
    var aim: Int
)

class Submarine {
    private val position = Position(0, 0, 0)

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

    val currentPosition: Int
        get() = position.horizontal * position.depth
}