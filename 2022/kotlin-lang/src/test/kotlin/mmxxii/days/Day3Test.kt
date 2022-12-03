package mmxxii.days

import org.junit.jupiter.api.Test

import kotlin.test.expect

class Day3Test {

    private val testInput = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw",
    )

    @Test
    fun `part 1 should return 157 on test input`() = expect(157) {
        Day3().part1(testInput)
    }

    @Test
    fun `part 2 should return 70 on test input`() = expect(70) {
        Day3().part2(testInput)
    }
}