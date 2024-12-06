import java.io.FileNotFoundException

private const val BASE_LENGTH = 60

abstract class AbstractDay<T>(
    private val day: String,
    private val year: Int,
    private val title: String,
    private val testValue1: T? = null,
    private val testValue2: T? = null
) {

    abstract fun part1(input: List<String>): T
    abstract fun part2(input: List<String>): T

    fun runWithTest() {
        val testInput = readInput("Day${day}_test", year)
        val testResult1 = part1(testInput)
        val testResult2 = part2(testInput)
        assert(testValue1?.let { testResult1 == testValue1 } != false) {
            "Expected $testValue1, got $testResult1"
        }
        assert(testValue2?.let { testResult2 == testValue2 } != false) {
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
        val offset = title.length.takeIf{ it % 2 != 0 }?.let { 0  } ?: 1
        return "+".padEnd(padLength - offset, '-') + " ${title.green()} " + "+".padStart(padLength, '-')
    }

    private fun outputBuilder(title: String, result: String) =
        "| $title".padEnd(BASE_LENGTH - result.length - 1, ' ') + "${result.yellow()} |"
}


const val ANSI_RESET: String = "\u001B[0m";
const val ANSI_BLACK: String = "\u001B[30m";
const val ANSI_RED: String = "\u001B[31m";
const val ANSI_GREEN: String = "\u001B[32m";
const val ANSI_YELLOW: String = "\u001B[33m";
const val ANSI_BLUE: String = "\u001B[34m";
const val ANSI_PURPLE: String = "\u001B[35m";
const val ANSI_CYAN: String = "\u001B[36m";
const val ANSI_WHITE: String = "\u001B[37m";
fun String.red() = "$ANSI_RED$this$ANSI_RESET"
fun String.green() = "$ANSI_GREEN$this$ANSI_RESET"
fun String.yellow() = "$ANSI_YELLOW$this$ANSI_RESET"