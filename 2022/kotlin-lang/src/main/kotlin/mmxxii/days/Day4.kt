package mmxxii.days

class Day4 : Abstract2022<Int>("04", "Camp Cleanup") {
    override fun part1(input: List<String>) = input
        .map(String::toElvesPair)
        .count(Sequence<IntRange>::completeOverlap)

    override fun part2(input: List<String>) = input
        .map(String::toElvesPair)
        .count(Sequence<IntRange>::anyOverlap)
}

fun String.toElvesPair() = Regex("""(\d+)-(\d+)""")
    .findAll(this)
    .map(MatchResult::groups)
    .map {
        it
            .drop(1)
            .filterNotNull()
            .map(MatchGroup::value)
            .map(String::toInt)
            .run { IntRange(first(), last()) }
    }

fun Sequence<IntRange>.completeOverlap() = overlapWithPredicator(Iterable<Int>::all)

fun Sequence<IntRange>.anyOverlap() = overlapWithPredicator(Iterable<Int>::any)

fun Sequence<IntRange>.overlapWithPredicator(predicator: Iterable<Int>.(predicate: (Int) -> Boolean) -> Boolean) =
    first().predicator { it in last() } || last().predicator { it in first() }