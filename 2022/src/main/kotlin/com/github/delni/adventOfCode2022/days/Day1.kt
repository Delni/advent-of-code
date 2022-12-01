package com.github.delni.adventOfCode2022.days

class Day1: Abstract2022("01", "Calorie Counting") {
    override fun part1(input: List<String>): Int {
        return input
            .toInventories()
            .map(List<Int>::sum)
            .maxOf { it }
    }

    override fun part2(input: List<String>): Int {
        return input
            .toInventories()
            .map(List<Int>::sum)
            .sortedDescending()
            .take(3)
            .sum()
    }

    private fun List<String>.toInventories(): List<List<Int>> {
        var lastIndex = 0
        val inventories = mutableListOf<List<String>>()
        forEachIndexed { index, it ->
            it.takeIf { it == "" }?.also {
                inventories.add(subList(lastIndex, index))
                lastIndex = index + 1
            }
        }
        inventories.add(subList(lastIndex, size - 1))
        return inventories.map { it.map(Integer::valueOf) }
    }
}