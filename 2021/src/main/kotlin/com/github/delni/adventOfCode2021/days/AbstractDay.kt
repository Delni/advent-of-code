package com.github.delni.adventOfCode2021.days

import com.andreapivetta.kolor.cyan
import com.andreapivetta.kolor.green
import com.andreapivetta.kolor.yellow
import com.github.delni.adventOfCode2021.readInput


abstract class AbstractDay(
    private val DAY: String,
    private val TITLE: String,
    private val TEST_VALUE_1: Int? = null,
    private val TEST_VALUE_2: Int? = null
) {

    abstract fun part1(input: List<String>): Int
    abstract fun part2(input: List<String>): Int

    init { run() }


    private fun run() {
        val testInput = readInput("Day${DAY}_test")
        val testResult1 = part1(testInput)
        val testResult2 = part2(testInput)
        assert(TEST_VALUE_1?.let { testResult1 == TEST_VALUE_1} ?: true) {
            "Expected $TEST_VALUE_1, got $testResult1"
        }
        assert(TEST_VALUE_2?.let { testResult2 == TEST_VALUE_2} ?: true) {
            "Expected $TEST_VALUE_2, got $testResult2"
        }

        val input = readInput("Day${DAY}")
        val emphasizedText = "DAY$DAY: $TITLE".green()
        val title = "+-------- $emphasizedText --------+"
        val part1Result = part1(input).toString().yellow()
        val part2Result = part2(input).toString().yellow()

        println(title)
        println("| Part 01 ".padEnd(title.length - part1Result.length - 3, ' ') + " $part1Result |")
        println("| Part 02 ".padEnd(title.length - part2Result.length - 3, ' ') + " $part2Result |")
        println("".cyan().padEnd(title.length, '-'))
    }
}