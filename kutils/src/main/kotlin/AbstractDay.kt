import com.andreapivetta.kolor.cyan
import com.andreapivetta.kolor.green
import com.andreapivetta.kolor.yellow

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
        assert(testValue1?.let { testResult1 == testValue1} ?: true) {
            "Expected $testValue1, got $testResult1"
        }
        assert(testValue2?.let { testResult2 == testValue2} ?: true) {
            "Expected $testValue2, got $testResult2"
        }
        run()
    }
    fun run() {
        val input = readInput("Day${day}", year)
        val emphasizedText = "DAY$day: $title".green()
        val title = "+-------- $emphasizedText --------+"
        val part1Result = part1(input).toString().yellow()
        val part2Result = part2(input).toString().yellow()

        println(title)
        println("| Part 01 ".padEnd(title.length - part1Result.length - 3, ' ') + " $part1Result |")
        println("| Part 02 ".padEnd(title.length - part2Result.length - 3, ' ') + " $part2Result |")
        println("".cyan().padEnd(title.length, '-'))
    }
}