package mmxxii.days

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

import kotlin.test.expect

class Day4Test {

    private val testInput = listOf(
        "2-4,6-8",
        "2-3,4-5",
        "5-7,7-9",
        "2-8,3-7",
        "6-6,4-6",
        "2-6,4-8",
    )

    @Test
    fun `part 1 should return 2 on test input`() = expect(2) {
        Day4().part1(testInput)
    }

    @Test
    fun `part 2 should return 4 on test input`() = expect(4) {
        Day4().part2(testInput)
    }

    @Nested
    inner class Helpers {
        @Test
        fun `should parse string into elves pair of ranges`() = expect(listOf(2..4, 6..8)) {
            "2-4,6-8".toElvesPair().toList()
        }
    }

}