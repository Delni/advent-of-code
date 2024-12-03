package days

import AbstractDay

class Day3 : AbstractDay<Int>("03", 2024, "Mull It Over") {
    override fun part1(input: List<String>) = input
        .joinToString("")
        .let {
            "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex().findAll(it)
        }.map { matchResult ->
            matchResult.groupValues[1].toInt() * matchResult.groupValues[2].toInt()
        }.reduce<Int, Int>(Int::plus)

    override fun part2(input: List<String>) = input.joinToString("")
        .let { string ->
            "^(.+)?don't\\(\\)|do\\(\\)(.+)?(don't\\(\\))*$".toRegex()
                .findAll(string)
                .map { matchResult ->
                    matchResult
                        .groupValues
                        .takeLast(3)
                        .first(String::isNotEmpty)
                }.map { string ->
                    part1(listOf(string))
                }.reduce<Int, Int>(Int::plus)
        }

}