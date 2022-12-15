package mmxxii.days

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.awt.Point
import java.io.File
import kotlin.test.expect

internal class Day15Test {
    private val testInput: List<String> = listOf(
        "Sensor at x=2, y=18: closest beacon is at x=-2, y=15",
        "Sensor at x=9, y=16: closest beacon is at x=10, y=16",
        "Sensor at x=13, y=2: closest beacon is at x=15, y=3",
        "Sensor at x=12, y=14: closest beacon is at x=10, y=16",
        "Sensor at x=10, y=20: closest beacon is at x=10, y=16",
        "Sensor at x=14, y=17: closest beacon is at x=10, y=16",
        "Sensor at x=8, y=7: closest beacon is at x=2, y=10",
        "Sensor at x=2, y=0: closest beacon is at x=2, y=10",
        "Sensor at x=0, y=11: closest beacon is at x=2, y=10",
        "Sensor at x=20, y=14: closest beacon is at x=25, y=17",
        "Sensor at x=17, y=20: closest beacon is at x=21, y=22",
        "Sensor at x=16, y=7: closest beacon is at x=15, y=3",
        "Sensor at x=14, y=3: closest beacon is at x=15, y=3",
        "Sensor at x=20, y=1: closest beacon is at x=15, y=3",
    )

    @Test
    fun `part 1 should return 26 on test input`() = expect(26) {
        Day15(offset = 10).part1(testInput)
    }

    @Test
    fun `part 2 should return 93 on test input`() = expect(93) {
        Day15().part2(testInput)
    }


    @Nested
    inner class Parser {
        @Test
        fun `should return Sensor and Beacon`() = expect(
            Sensor(Point(2, 18), 7)
                    to
                    Beacon(Point(-2, 15))
        ) {
            "Sensor at x=2, y=18: closest beacon is at x=-2, y=15".toSensorsAndBeacons().first()
        }

    }

    @Nested
    inner class OnProdInputs {
        private val input = File("../resources", "Day15.txt").readLines()

        @Test
        fun `part 1 should return 808 on prod input`() = expect(808) {
            Day15().part1(input)
        }

        @Test
        fun `part 2 should return 26625 on prod input`() = expect(26625) {
            Day15().part2(input)
        }
    }

}