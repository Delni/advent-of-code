package mmxxii.days

class Day3 : Abstract2022("03", "Rucksack Reorganization") {
    override fun part1(input: List<String>): Int = input
        .map(String::toRucksack)
        .map { it.first.intersect(it.second.toSet()).single() }
        .sumByPriority()

    override fun part2(input: List<String>) = input
        .toSquad()
        .map { it[0].intersect(it[1]).intersect(it[2]).single() }
        .sumByPriority()


    private fun List<String>.toPriority() = map { it.toCharArray().single().code }.map {
        it.takeIf { it >= 'a'.code }?.let { char -> char - 'a'.code + 1 } ?: (it - 'A'.code + 27)
    }

    private fun List<String>.sumByPriority() = toPriority().sum()
}

private fun String.toRucksack() = split("").let {
    it.subList(1, it.size / 2) to it.subList(it.size / 2, it.size - 1)
}

private fun List<String>.toSquad() = List(size) { index ->
    index.takeIf { it != 0 && (it + 1) % 3 == 0 }?.let {
        subList(it - 2, it + 1).map { s -> s.split("").filter(String::isNotEmpty).toSet() }
    }
}.filterNotNull()