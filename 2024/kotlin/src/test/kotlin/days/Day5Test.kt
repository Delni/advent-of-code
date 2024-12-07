package days

import org.junit.jupiter.api.Test
import kotlin.test.expect

internal class Day5Test {
    private val testInput: List<String> = listOf(
        "47|53",
        "97|13",
        "97|61",
        "97|47",
        "75|29",
        "61|13",
        "75|53",
        "29|13",
        "97|29",
        "53|29",
        "61|53",
        "97|53",
        "61|29",
        "47|13",
        "75|47",
        "97|75",
        "47|61",
        "75|61",
        "47|29",
        "75|13",
        "53|13",
        "",
        "75,47,61,53,29",
        "97,61,53,29,13",
        "75,29,13",
        "75,97,47,61,53",
        "61,13,29",
        "97,13,75,29,47",
    )

    @Test
    fun `part 1 should return 143 on test input`() = expect(143) {
        Day5().part1(testInput)
    }

    @Test
    fun `part 2 should return 123 on test input`() = expect(123) {
        Day5().part2(testInput)
    }

}