import com.andreapivetta.kolor.green
import com.andreapivetta.kolor.red
import com.andreapivetta.kolor.yellow
import java.io.FileNotFoundException

private const val BASE_LENGTH = 60

abstract class AbstractDay(
    private val day: String,
    private val year: Int,
    private val title: String,
    private val testValue1: Int? = null,
    private val testValue2: Int? = null
) {

    abstract fun part1(input: List<String>): Int
    abstract fun part2(input: List<String>): Int

    fun runWithTest() {
        val testInput = readInput("Day${day}_test", year)
        val testResult1 = part1(testInput)
        val testResult2 = part2(testInput)
        assert(testValue1?.let { testResult1 == testValue1 } ?: true) {
            "Expected $testValue1, got $testResult1"
        }
        assert(testValue2?.let { testResult2 == testValue2 } ?: true) {
            "Expected $testValue2, got $testResult2"
        }
        run()
    }

    fun run() {
        println(titleBuilder("DAY $day: $title"))
        try {
            val input = readInput("Day${day}", year)
            println(outputBuilder("Part 01", "${part1(input)}"))
            println(outputBuilder("Part 02", "${part2(input)}"))
        } catch (e: FileNotFoundException) {
            println(("| " + e.message?.red()).padEnd(BASE_LENGTH + 8) + " |")
        }
        println("+".padEnd(BASE_LENGTH, '-') + "+")
    }

    private fun titleBuilder(title: String): String {
        val padLength = maxOf(BASE_LENGTH - title.length, 0) / 2
        return "+".padEnd(padLength, '-') + " ${title.green()} " + "+".padStart(padLength, '-')
    }

    private fun outputBuilder(title: String, result: String) =
        "| $title".padEnd(BASE_LENGTH - result.length - 1, ' ') + "${result.yellow()} |"
}