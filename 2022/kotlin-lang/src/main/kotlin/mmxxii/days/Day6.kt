package mmxxii.days

class Day6 : Abstract2022<Int>("06", "Tuning Trouble") {
    override fun part1(input: List<String>) = input.first() findMarkerWithLength 4

    override fun part2(input: List<String>) = input.first() findMarkerWithLength 14

    private infix fun String.findMarkerWithLength(offset: Int) = splitNotEmpty("")
        .dropLast(offset)
        .mapIndexedNotNull { index, _ ->
            substring(index, index + offset)
                .splitNotEmpty("")
                .takeIf { it.size == it.toSet().size }
                ?.let { index + offset }
        }
        .first()
    private fun CharSequence.splitNotEmpty(delimiter: String) = split(delimiter).filter(String::isNotBlank)
}

