package com.github.delni.adventOfCode2021.days

import com.github.delni.adventOfCode2021.submarine.Direction
import com.github.delni.adventOfCode2021.submarine.Submarine
import java.lang.Integer.parseInt


class DayTwo : AbstractDay("02", "Dive !", 150, 900) {

    override fun part1(input: List<String>): Int {
        val submarine = Submarine()
        input
            .map { it.toStep() }
            .forEach { submarine moveSimplyTo it }
        return submarine.currentPosition
    }

    override fun part2(input: List<String>): Int {
        val submarine = Submarine()
        input
            .map { it.toStep() }
            .forEach { submarine moveTo it }
        return submarine.currentPosition
    }

    private fun String.toStep() = uppercase()
        .split(" ")
        .let { Direction.valueOf(it.first()) to parseInt(it.last()) }
}