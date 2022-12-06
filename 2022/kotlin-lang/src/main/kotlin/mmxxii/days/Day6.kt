package mmxxii.days

class Day6: Abstract2022<Int>("06", "Tuning Trouble") {
    override fun part1(input: List<String>) = input
        .first()
        .split("")
        .filter(String::isNotBlank)
        .mapIndexedNotNull { index, _ ->
                val last4Chars = index
                    .takeIf { it >= 4 }
                    ?.let { input.first().substring(it - 4, it).split("") }
                    ?.filter(String::isNotBlank)
                last4Chars?.toSet()?.takeIf { it.size == last4Chars.size }?.let { index }
        }
        .first()

    override fun part2(input: List<String>) = input
        .first()
        .split("")
        .filter(String::isNotBlank)
        .mapIndexedNotNull { index, _ ->
            val last4Chars = index
                .takeIf { it >= 14 }
                ?.let { input.first().substring(it - 14, it).split("") }
                ?.filter(String::isNotBlank)
            last4Chars?.toSet()?.takeIf { it.size == last4Chars.size }?.let { index }
        }
        .first()
}