package days

import org.junit.jupiter.api.Test
import kotlin.test.expect

internal class Day4Test {
    private val testInput: List<String> = listOf(
        "MMMSXXMASM",
        "MSAMXMSMSA",
        "AMXSXMAAMM",
        "MSAMASMSMX",
        "XMASAMXAMM",
        "XXAMMXXAMA",
        "SMSMSASXSS",
        "SAXAMASAAA",
        "MAMMMXMMMM",
        "MXMXAXMASX",
    )

    @Test
    fun `part 1 should return 18 on test input`() = expect(18) {
        Day4().part1(testInput)
    }

    @Test
    fun `part 2 should return 9 on test input`() = expect(9) {
        Day4().part2(testInput)
    }

}