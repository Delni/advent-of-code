package com.github.delni.advent_of_code.days

import com.andreapivetta.kolor.cyan
import com.andreapivetta.kolor.green
import com.andreapivetta.kolor.yellow
import com.github.delni.advent_of_code.readInput


abstract class AbstractDay(private val DAY: String, private val TITLE: String, private val TEST_VALUE_1: Int, private val TEST_VALUE_2: Int,) {

    abstract fun part1(input: List<String>): Int
    abstract fun part2(input: List<String>): Int

    init {
        run()
    }


    fun run() {
        val testInput = readInput("Day${DAY}_test")
        assert(part1(testInput) == TEST_VALUE_1)
        assert(part2(testInput) == TEST_VALUE_2)

        val input = readInput("Day${DAY}")
        val emphasizedText = "DAY $DAY : $TITLE".green()
        val title = "+-------- $emphasizedText --------+"
        println(title)
        val part1Result = part1(input).toString().yellow()
        val part2Result = part2(input).toString().yellow()
        println("| Part 01 ".padEnd(title.length - part1Result.length - 3 , ' ')  + " $part1Result |")
        println("| Part 02 ".padEnd(title.length - part2Result.length - 3, ' ')  + " $part2Result |")
        println("".cyan().padEnd(title.length, '-'))
    }
}