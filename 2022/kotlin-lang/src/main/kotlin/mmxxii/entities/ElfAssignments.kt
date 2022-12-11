package mmxxii.entities


typealias ElfAssignments = IntRange

fun String.toElvesPair() = Regex("""(\d+)-(\d+)""")
    .findAll(this)
    .map(MatchResult::groups)
    .map {
        it
            .drop(1)
            .filterNotNull()
            .map(MatchGroup::value)
            .map(String::toInt)
            .run { ElfAssignments(first(), last()) }
    }

fun Sequence<ElfAssignments>.completeOverlap() = overlapWithPredicator(Iterable<Int>::all)

fun Sequence<ElfAssignments>.anyOverlap() = overlapWithPredicator(Iterable<Int>::any)

fun Sequence<ElfAssignments>.overlapWithPredicator(predicator: Iterable<Int>.(predicate: (Int) -> Boolean) -> Boolean) =
    first().predicator { it in last() } || last().predicator { it in first() }