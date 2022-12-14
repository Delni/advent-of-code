package mmxxii.days

class Day13 : Abstract2022<Int>("13", "Distress Signal") {
    override fun part1(input: List<String>): Int = input
        .toPairsOfPackets()
        .mapIndexedNotNull { index, pair ->
            index
        }.sum()

    override fun part2(input: List<String>): Int = input.size

    fun List<String>.toPairsOfPackets() = filter { it.isNotBlank() }
        .windowed(2, 2)
        .map { pair ->
            Packet(pair.first()) to Packet(pair.last())
        }
}

data class Packet(val data: Any) : Comparable<Any> {
    override fun compareTo(other: Any): Int {
        TODO("Not yet implemented")
    }
}


fun String.toData(): List<Any> {
    val innerString = Regex("""\[(.*)]""").find(this)?.groupValues?.get(1) ?: this

    return innerString.split(",").map(String::trim).filter(String::isNotEmpty)
}