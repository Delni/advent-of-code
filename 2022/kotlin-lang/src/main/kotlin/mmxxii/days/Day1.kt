package mmxxii.days

class Day1 : Abstract2022<Int>("01", "Calorie Counting") {
    override fun part1(input: List<String>): Int = input
        .toInventories()
        .map(List<Int>::sum)
        .maxOf { it }

    override fun part2(input: List<String>): Int = input
        .toInventories()
        .map(List<Int>::sum)
        .sortedDescending()
        .take(3)
        .sum()

    private fun List<String>.toInventories() = joinToString("#")
        .split("##")
        .map { it.split("#") }
        .map { it.map(String::toInt) }
}