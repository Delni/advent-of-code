package days

import org.junit.jupiter.api.Test
import kotlin.test.expect

internal class Day1Test {
    private val testInput: List<String> = listOf(
        "3   4",
        "4   3",
        "2   5",
        "1   3",
        "3   9",
        "3   3",
    )
    @Test
    fun `part 1 should return 11 on test input`() = expect(11) {
        Day1().part1(testInput)
    }

    @Test
    fun `part 2 should return 31 on test input`() = expect(31) {
        Day1().part2(testInput)
    }

}