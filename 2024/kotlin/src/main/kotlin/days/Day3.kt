package days

import AbstractDay

class Day3 : AbstractDay<Int>("03", 2024, "Mull It Over") {
    override fun part1(input: List<String>) = input
        .joinToString("")
        .let {
            "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex().findAll(it)
        }.map { matchResult ->
            matchResult.groupValues[1].toInt() * matchResult.groupValues[2].toInt()
        }.sumOf { it }

    override fun part2(input: List<String>) = input.joinToString("")
        .let { string ->
            "do\\(\\)|don't\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
                .findAll(string)
                .fold(0 to true) { acc, element ->
                    when {
                        element.value.startsWith("mul") and acc.second -> acc.first + element.groupValues[1].toInt() * element.groupValues[2].toInt() to acc.second
                        element.value.startsWith("don't") -> acc.first to false
                        element.value.startsWith("do") -> acc.first to true
                        else -> acc
                    }
                }.first
        }
}