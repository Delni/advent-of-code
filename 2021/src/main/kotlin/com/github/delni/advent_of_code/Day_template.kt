package com.github.delni.advent_of_code

/**
 * --- Day 1: Sonar Sweep ---
 */
fun main() {
    val DAY = "01"
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${DAY}_test")
    assert(part1(testInput) == 7)
    assert(part2(testInput) == 5)

    val input = readInput("Day${DAY}")
    println("------- DAY $DAY -------")
    val part1Result = part1(input).toString()
    val part2Result = part2(input).toString()
    println("Part 01 ".padEnd(21 - part1Result.length , '-')  + " $part1Result")
    println("Part 02 ".padEnd(21 - part1Result.length , '-')  + " $part2Result")
}