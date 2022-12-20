package mmxxii.days

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.expect

internal class Day18Test {
    private val testInput: List<String> = listOf(
        "2,2,2",
        "1,2,2",
        "3,2,2",
        "2,1,2",
        "2,3,2",
        "2,2,1",
        "2,2,3",
        "2,2,4",
        "2,2,6",
        "1,2,5",
        "3,2,5",
        "2,1,5",
        "2,3,5",
    )

    @Test
    fun `part 1 should return 64 on test input`() = expect(64) {
        Day18().part1(testInput)
    }

    @Test
    fun `part 1 should return 10 on small input`() = expect(10) {
        Day18().part1(listOf("1,1,1", "2,1,1"))
    }

    @Test
    fun `part 2 should return 58 on test input`() = expect(58) {
        Day18().part2(testInput)
    }

    @Nested
    inner class OnProdInputs {
        private val input = File("../resources", "Day18.txt").readLines()

        @Test
        fun `part 1 should return 4608 on prod input`() = expect(4608) {
            Day18().part1(input)
        }

        @Test
        fun `part 2 should return 26625 on prod input`() = expect(26625) {
            Day18().part2(input)
        }
    }

}