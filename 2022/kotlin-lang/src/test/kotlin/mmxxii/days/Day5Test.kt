package mmxxii.days

import org.junit.jupiter.api.Test

import kotlin.test.expect

class Day5Test {

    private val testInput = listOf(
        "    [D]    ",
        "[N] [C]    ",
        "[Z] [M] [P]",
        " 1   2   3 ",
        "",
        "move 1 from 2 to 1",
        "move 3 from 1 to 3",
        "move 2 from 2 to 1",
        "move 1 from 1 to 2",
    )

    @Test
    fun `part 1 should return "CMZ" on test input`() = expect("CMZ") {
        Day5().part1(testInput)
    }

    @Test
    fun `part 2 should return 4 on test input`() = expect("MCD") {
        Day5().part2(testInput)
    }

}