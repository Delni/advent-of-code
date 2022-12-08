package mmxxii.days

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

import kotlin.test.expect

class Day8Test {

    private val testInput = listOf(
        "30373",
        "25512",
        "65332",
        "33549",
        "35390",
    )

    @Test
    fun `part 1 should return 21 on test input`() = expect(21) {
        Day8().part1(testInput)
    }

    @Test
    fun `part 2 should return 8 on test input`() = expect(8) {
        Day8().part2(testInput)
    }

    @Nested
    inner class Vision {
        @Test
        fun `should return first tree blocking the view`() {
            val tree = 5
            expect(2) {
                tree.canSeeFarWithTrees(listOf(2, 5, 2))
            }

            expect(1) {
                tree.canSeeFarWithTrees(listOf(5, 2))
            }

            expect(2) {
                tree.canSeeFarWithTrees(listOf(0, 2))
            }
        }

        @Test
        fun `should return all trees if none is blocking the view`() {
            val tree = 5
            expect(2) {
                tree.canSeeFarWithTrees(listOf(0, 2))
            }
        }
    }

    @Nested
    inner class OnProdInputs {
        private val input = File("../resources", "Day08.txt").readLines()

        @Test
        fun `part 1 should return 1711 on test input`() = expect(1711) {
            Day8().part1(input)
        }

        @Test
        fun `part 2 should return 301392 on test input`() = expect(301392) {
            Day8().part2(input)
        }
    }
}