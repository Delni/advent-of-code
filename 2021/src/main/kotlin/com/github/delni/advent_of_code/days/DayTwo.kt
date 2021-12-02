package com.github.delni.advent_of_code.days

import java.lang.Integer.parseInt


enum class Position {
    HORIZONTAL,
    VERTICAL,
    AIM
}

enum class Direction {
    FORWARD,
    DOWN,
    UP
}
class DayTwo: AbstractDay("02", "Dive !", 150, 900) {
    override fun part1(input: List<String>): Int {
        val position = mutableMapOf(
            Position.HORIZONTAL to 0,
            Position.VERTICAL to 0
        )
        input.map { Direction.valueOf(it.split(" ")[0].uppercase()) to parseInt(it.split(" ")[1]) }
        .forEach {
            when (it.first) {
                Direction.FORWARD -> position.replace(Position.HORIZONTAL, position[Position.HORIZONTAL]!!.plus(it.second))
                Direction.DOWN -> position.replace(Position.VERTICAL, position[Position.VERTICAL]!!.plus(it.second))
                Direction.UP -> position.replace(Position.VERTICAL, position[Position.VERTICAL]!!.minus(it.second))
            }

        }
        return position[Position.HORIZONTAL]!! * position[Position.VERTICAL]!!
    }

    override fun part2(input: List<String>): Int {
        val position = mutableMapOf(
            Position.HORIZONTAL to 0,
            Position.VERTICAL to 0,
            Position.AIM to 0
        )
        input.map { Direction.valueOf(it.split(" ")[0].uppercase()) to parseInt(it.split(" ")[1]) }
            .forEach {
                when (it.first) {
                    Direction.FORWARD -> {
                        position.replace(Position.HORIZONTAL, position[Position.HORIZONTAL]!!.plus(it.second))
                        position.replace(Position.VERTICAL, position[Position.VERTICAL]!!.plus(it.second * position[Position.AIM]!!))
                    }
                    Direction.DOWN -> position.replace(Position.AIM, position[Position.AIM]!!.plus(it.second))
                    Direction.UP -> position.replace(Position.AIM, position[Position.AIM]!!.minus(it.second))
                }

            }
        return position[Position.HORIZONTAL]!! * position[Position.VERTICAL]!!
    }

}