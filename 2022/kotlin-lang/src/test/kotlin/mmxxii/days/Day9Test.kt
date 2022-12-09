package mmxxii.days

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

import kotlin.test.expect

class Day9Test {

    private val testInput = listOf(
        "R 4",
        "U 4",
        "L 3",
        "D 1",
        "R 4",
        "D 1",
        "L 5",
        "R 2",
    )

    @Test
    fun `part 1 should return 13 on test input`() = expect(13) {
        Day9().part1(testInput)
    }

    @Test
    fun `part 2 should return 1 on test input`() = expect(1) {
        Day9().part2(testInput)
    }

    @Test
    fun `part 2 should return 36 on longer input`() = expect(36) {
        Day9().part2(
            listOf(
                "R 5",
                "U 8",
                "L 8",
                "D 3",
                "R 17",
                "D 10",
                "L 25",
                "U 20",
            )
        )
    }

    @Nested
    inner class OnProdInputs {
        private val input = File("../resources", "Day09.txt").readLines()

        @Test
        fun `part 1 should return 6236 on test input`() = expect(6236) {
            Day9().part1(input)
        }

        @Test
        fun `part 2 should return 2449 on test input`() = expect(2449) {
            Day9().part2(input)
        }
    }
}