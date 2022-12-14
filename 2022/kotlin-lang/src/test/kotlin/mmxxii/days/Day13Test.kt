package mmxxii.days

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.expect

internal class Day13Test {
    private val testInput: List<String> = listOf(
        "[1, 1, 3, 1, 1]",
        "[1, 1, 5, 1, 1]",
        "",
        "[[1],[2,3,4]]",
        "[[1],4]",
        "",
        "[9]",
        "[[8,7,6]]",
        "",
        "[[4,4],4,4]",
        "[[4,4],4,4,4]",
        "",
        "[7,7,7,7]",
        "[7,7,7]",
        "",
        "[]",
        "[3]",
        "",
        "[[[]]]",
        "[[]]",
        "",
        "[1,[2,[3,[4,[5,6,7]]]],8,9]",
        "[1,[2,[3,[4,[5,6,0]]]],8,9]",
    )

    @Test
    fun `part 1 should return 13 on test input`() = expect(13) {
        Day13().part1(testInput)
    }

    @Test
    fun `part 2 should return 29 on test input`() = expect(29) {
        Day13().part2(testInput)
    }


    @Nested
    inner class Parser {
        @Test
        fun `should parse simple list as a list`() = expect(listOf(1, 1, 3, 1, 1)) {
            "[1, 1, 3, 1, 1]".toData()
        }

        @Test
        fun `should parse nested list as a list of list`() = expect(listOf(listOf(4, 4), 4, 4)) {
            "[[4,4],4,4]".toData()
        }

        @Test
        fun `should parse multi layer of input`() = expect(
            listOf(
                1,
                listOf(
                    2,
                    listOf(
                        3,
                        listOf(
                            4,
                            listOf(5, 6, 7)
                        )
                    )
                ),
                8,
                9
            )
        ) {
            "[1,[2,[3,[4,[5,6,7]]]],8,9]".toData()
        }
    }

    @Nested
    inner class OnProdInputs {
        private val input = File("../resources", "Day13.txt").readLines()

        @Test
        fun `part 1 should return 350 on prod input`() = expect(350) {
            Day13().part1(input)
        }

        @Test
        fun `part 2 should return 1 on prod input`() = expect(1) {
            Day13().part1(input)
        }
    }

}