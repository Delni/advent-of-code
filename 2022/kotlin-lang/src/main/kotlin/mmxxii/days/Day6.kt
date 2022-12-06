package mmxxii.days

class Day6 : Abstract2022<Int>("06", "Tuning Trouble") {
    override fun part1(input: List<String>) = input.first() findMarkerWithLength 4

    override fun part2(input: List<String>) = input.first() findMarkerWithLength 14

    private infix fun String.findMarkerWithLength(offset: Int) = windowed(offset)
        .mapIndexedNotNull { index, s ->
            index
                .takeIf { s.length == s.toSet().size }
                ?.let { it + offset }
        }
        .first()

}

