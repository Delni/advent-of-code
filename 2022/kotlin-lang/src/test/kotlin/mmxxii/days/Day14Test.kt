package mmxxii.days

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.awt.Point
import java.io.File
import kotlin.test.expect

internal class Day14Test {
    private val testInput: List<String> = listOf(
        "498,4 -> 498,6 -> 496,6",
        "503,4 -> 502,4 -> 502,9 -> 494,9",
    )

    @Test
    fun `part 1 should return 24 on test input`() = expect(24) {
        Day14().part1(testInput)
    }

    @Test
    fun `part 2 should return 93 on test input`() = expect(93) {
        Day14().part2(testInput)
    }


    @Nested
    inner class Parser {
        @Test
        fun `should return path`() = expect(listOf(Point(498,4) to Point(498,6), Point(498,6) to Point(496,6))) {
            "498,4 -> 498,6 -> 496,6".toPath()
        }

    }

    @Nested
    inner class OnProdInputs {
        private val input = File("../resources", "day14.txt").readLines()

        @Test
        fun `part 1 should return 808 on prod input`() = expect(808) {
            Day14().part1(input)
        }

        @Test
        fun `part 2 should return 26625 on prod input`() = expect(26625) {
            Day14().part2(input)
        }
    }

}