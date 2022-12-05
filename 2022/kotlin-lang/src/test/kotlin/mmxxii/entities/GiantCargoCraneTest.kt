package mmxxii.entities

import org.junit.jupiter.api.Test
import kotlin.test.expect

class GiantCargoCraneTest {
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
    fun `should create CraneMove in natural language`() = expect( 1 from 2 onto 1) {
        CraneMove(1, 2, 1)
    }

    @Test
    fun `should initialize stacks and moves`() {
        val testCrane = testInput.toGiantCargoCrane()
        expect(listOf(
            listOf('Z', 'N'),
            listOf('M', 'C', 'D'),
            listOf('P'))
        ) {
            testCrane.stacks
        }
        expect(listOf(
            1 from 2 onto 1,
            3 from 1 onto 3,
            2 from 2 onto 1,
            1 from 1 onto 2,
        )) {
            testCrane.moves
        }

    }
}