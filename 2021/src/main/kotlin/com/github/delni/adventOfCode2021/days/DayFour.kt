package com.github.delni.adventOfCode2021.days

class DayFour: Abstract2021("04", "Giant Squid", 4512) {
    override fun part1(input: List<String>): Int {
        val draw = input.first().split(',').map(Integer::parseInt)
        val boards = input.drop(2).fold(mutableListOf(mutableListOf<List<Int>>())) { acc, elem ->
            elem.takeUnless { it == "" }?.let { acc.last().add(it.split(Regex("\\s+")).map(Integer::parseInt)) } ?: acc.add(mutableListOf())
            acc
        }

        var lastDraw = -1
        draw.forEach {
            boards.forEach { board ->
                board.forEach { row ->
                    row
                }
            }
        }
        return -1
    }

    override fun part2(input: List<String>): Int {
        return -1
    }
}

fun makeBingoList(input: List<String>) {

}