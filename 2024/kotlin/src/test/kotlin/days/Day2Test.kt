package days

import org.junit.jupiter.api.Test
import kotlin.test.expect

internal class Day2Test {
    private val testInput: List<String> = listOf(
        "7 6 4 2 1",
        "1 2 7 8 9",
        "9 7 6 2 1",
        "1 3 2 4 5",
        "8 6 4 4 1",
        "1 3 6 7 9",
    )
    @Test
    fun `part 1 should return 2 on test input`() = expect(2) {
        Day2().part1(testInput)
    }

    @Test
    fun `part 2 should return 4 on test input`() = expect(4) {
        Day2().part2(testInput)
    }

}