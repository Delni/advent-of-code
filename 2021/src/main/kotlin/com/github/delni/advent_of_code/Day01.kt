package com.github.delni.advent_of_code

/**
 * --- Day 1: Sonar Sweep ---
 */
fun main() {
    fun getValueWithOffset(offset: Int, index: Int, input: List<String>): Int {
        return (index.takeIf { it < input.size - offset }?.let { input[it + offset] }?.let(Integer::parseInt) ?: 0)
    }

    fun part1(input: List<String>): Int {
        return input
            .map(Integer::parseInt)
            .foldIndexed(0) { index, accumulator, element ->
                val previous = index.takeIf { it >= 1 }?.let { Integer.parseInt(input[it - 1]) }
                element
                    .takeIf { it > (previous ?: it) }
                    ?.let { accumulator + 1 } ?: accumulator
            }
    }

    fun part2(input: List<String>): Int {
        val summedInput = input
            .map(Integer::parseInt)
            .mapIndexed { index, value ->
                value + getValueWithOffset(1, index, input) + getValueWithOffset(2, index, input)
            }
        return summedInput.foldIndexed(0) { index, accumulator, element ->
            val previous = index.takeIf { it >= 1 }?.let { summedInput[it - 1] }
            element.takeIf { it > (previous ?: it) }?.let {
                accumulator + 1
            } ?: accumulator
        }
    }

    val DAY = "01"
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
