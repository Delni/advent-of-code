package days

import org.junit.jupiter.api.Test
import kotlin.test.expect

internal class Day6Test {
    private val testInput: List<String> = listOf(
        "....#.....",
        ".........#",
        "..........",
        "..#.......",
        ".......#..",
        "..........",
        ".#..^.....",
        "........#.",
        "#.........",
        "......#...",
        )

    @Test
    fun `part 1 should return 41 on test input`() = expect(41) {
        Day6().part1(testInput)
    }

    @Test
    fun `part 2 should return 123 on test input`() = expect(123) {
        Day6().part2(testInput)
    }

}