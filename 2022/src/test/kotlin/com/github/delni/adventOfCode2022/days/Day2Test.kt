package com.github.delni.adventOfCode2022.days

import org.junit.jupiter.api.Test

import kotlin.test.expect

class Day2Test {

    private val testInput = listOf(
        "A Y",
        "B X",
        "C Z"
    )

    @Test
    fun `part 1 should return 15 on test input`() = expect(15) {
        Day2().part1(testInput)
    }

    @Test
    fun `part 2 should return 12 on test input`() = expect(12) {
        Day2().part2(testInput)
    }
}