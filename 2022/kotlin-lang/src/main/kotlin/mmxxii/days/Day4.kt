package mmxxii.days

class Day4 : Abstract2022("04", "Camp Cleanup") {
    override fun part1(input: List<String>) = input
        .map(String::toElvesPair)
        .count(List<IntRange>::overlapCompletely)

    override fun part2(input: List<String>) = input
        .map(String::toElvesPair)
        .count {
            it.first()
                .any { id -> id in it.last() } || it.last().any { id -> id in it.first() }
        }
}

fun String.toElvesPair(): List<IntRange> {
    val regex = Regex("(\\d+)-(\\d+)")
    return regex.findAll(this).toList().map {
        val bounds = it.groups
            .drop(1)
            .filterNotNull()
            .map(MatchGroup::value)
            .map(String::toInt)
        IntRange(bounds.first(), bounds.last())
    }
}

fun List<IntRange>.overlapCompletely() = first()
    .all { id -> id in last() } || last().all { id -> id in first() }