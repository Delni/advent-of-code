package days

import org.junit.jupiter.api.Test
import kotlin.test.expect

internal class Day3Test {
    @Test
    fun `part 1 should return 161 on test input`() = expect(161) {
        Day3().part1(
            listOf(
                "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
            )
        )
    }

    @Test
    fun `part 2 should return 48 on test input`() = expect(48) {
        Day3().part2(listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"))
    }

}