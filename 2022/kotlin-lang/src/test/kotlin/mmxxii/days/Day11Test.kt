package mmxxii.days

import mmxxii.entities.toMonkey
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.expect

class Day11Test {

    private val testInput = listOf(
        "Monkey 0:",
        "   Starting items: 79, 98",
        "   Operation: new = old * 19",
        "   Test: divisible by 23",
        "       If true: throw to monkey 2",
        "       If false: throw to monkey 3",
        "",
        "Monkey 1:",
        "   Starting items: 54, 65, 75, 74",
        "   Operation: new = old + 6",
        "   Test: divisible by 19",
        "       If true: throw to monkey 2",
        "       If false: throw to monkey 0",
        "",
        "Monkey 2:",
        "   Starting items: 79, 60, 97",
        "   Operation: new = old * old",
        "   Test: divisible by 13",
        "       If true: throw to monkey 1",
        "       If false: throw to monkey 3",
        "",
        "Monkey 3:",
        "   Starting items: 74",
        "   Operation: new = old + 3",
        "   Test: divisible by 17",
        "       If true: throw to monkey 0",
        "       If false: throw to monkey 1",
    )

    @Test
    fun `part 1 should return 10605 on test input`() = expect(10605L) {
        Day11().part1(testInput)
    }

    @Test
    fun `part 2 should return 2713310158 on test input`() = expect(2713310158) {
        Day11().part2(testInput)
    }

    @Nested
    inner class Parsing {
        private val testMonkey = listOf(
            "Monkey 0:",
            "   Starting items: 79, 98",
            "   Operation: new = old * 19",
            "   Test: divisible by 23",
            "       If true: throw to monkey 2",
            "       If false: throw to monkey 3",
            ""
        )

        @Test
        fun `should parse to Monkey`() {
            val monkey = testMonkey.toMonkey()
            expect(19) {
                monkey.operation(1)
            }

            expect(2) {
                monkey.test(23)
            }

            expect(3) {
                monkey.test(21)
            }
        }
    }

    @Nested
    inner class OnProdInputs {
        private val input = File("../resources", "Day11.txt").readLines()

        @Test
        fun `part 1 should return 55458 on prod input`() = expect(55458) {
            Day11().part1(input)
        }

        @Test
        fun `part 2 should return 14508081294 on prod input`() = expect(14508081294) {
            Day11().part2(input)
        }
    }
}