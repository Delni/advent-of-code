package mmxxii.days

import mmxxii.entities.anyOverlap
import mmxxii.entities.completeOverlap
import mmxxii.entities.toElvesPair

class Day4 : Abstract2022<Int>("04", "Camp Cleanup") {
    override fun part1(input: List<String>) = input
        .map(String::toElvesPair)
        .count(Sequence<IntRange>::completeOverlap)

    override fun part2(input: List<String>) = input
        .map(String::toElvesPair)
        .count(Sequence<IntRange>::anyOverlap)
}