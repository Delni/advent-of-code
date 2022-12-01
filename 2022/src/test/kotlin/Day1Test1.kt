import com.github.delni.adventOfCode2022.days.Day1
import org.junit.jupiter.api.Test
import kotlin.test.expect

internal class Day1Test {
    private val testInput: List<String> = listOf(
        "1000",
        "2000",
        "3000",
        "",
        "4000",
        "",
        "5000",
        "6000",
        "",
        "7000",
        "8000",
        "9000",
        "",
        "10000",
    )
    @Test
    fun `part 1 should return 24000 on test input`() {
        expect(24000) {
            Day1().part1(testInput)
        }
    }

    @Test
    fun `part 2 should return 45000 on test input`() {
        expect(45000) {
            Day1().part2(testInput)
        }
    }

}