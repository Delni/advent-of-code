import days.*

fun main() {
    listOf(
        Day1(),
        Day2(),
        Day3(),
        Day4(),
        Day5(),
    ).forEach(AbstractDay<out Any>::run)
}