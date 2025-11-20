package mmxxii.days

import mmxxii.entities.Boundary
import mmxxii.entities.findCoordsIn
import mmxxii.entities.getNeighborsIn
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.awt.Point
import java.io.File
import kotlin.test.expect

internal class Day12Test {
    private val testInput: List<String> = listOf(
        "Sabqponm",
        "abcryxxl",
        "accszExk",
        "acctuvwj",
        "abdefghi",
    )

    @Test
    fun `part 1 should return 31 on test input`() = expect(31) {
        Day12().part1(testInput)
    }

    @Test
    fun `part 2 should return 29 on test input`() = expect(29) {
        Day12().part2(testInput)
    }

    @Nested
    inner class HillClimbingTest {
        @Test
        fun `should find start`() = expect(Point(0, 0)) {
            'S' findCoordsIn testInput
        }

        @Test
        fun `should find neighbors`() {
            expect(listOf(Point(1, 0), Point(0, 1))) {
                'S'.findCoordsIn(testInput).getNeighborsIn(
                    Boundary(
                        topLeft = Point(0, 0),
                        bottomRight = Point(testInput.first().length, testInput.size)
                    )
                )
            }
        }
    }

    @Nested
    inner class OnProdInputs {
        private val input = File("../resources", "day12.txt").readLines()

        @Test
        fun `part 1 should return 350 on prod input`() = expect(350) {
            Day12().part1(input)
        }

        @Test
        fun `part 2 should return 349 on prod input`() = expect(349) {
            Day12().part2(input)
        }
    }

}