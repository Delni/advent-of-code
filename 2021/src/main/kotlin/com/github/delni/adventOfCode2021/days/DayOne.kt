package com.github.delni.adventOfCode2021.days

/**
 * --- Day 1: Sonar Sweep ---
 */
class DayOne: Abstract2021("01", "Sonar Sweep", 7, 5) {
    override fun part1(input: List<String>): Int {
        return input
            .map(Integer::parseInt)
            .foldIndexed(0) { index, accumulator, element ->
                val previous = index.takeIf { it >= 1 }?.let { Integer.parseInt(input[it - 1]) }
                element
                    .takeIf { it > (previous ?: it) }
                    ?.let { accumulator + 1 } ?: accumulator
            }
    }

    override fun part2(input: List<String>): Int {
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

    private fun getValueWithOffset(offset: Int, index: Int, input: List<String>): Int {
        return (index.takeIf { it < input.size - offset }?.let { input[it + offset] }?.let(Integer::parseInt) ?: 0)
    }

}