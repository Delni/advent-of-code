import days.*

fun main() {
    listOf(
        Day1(),
        Day2(),
        Day3(),
    ).forEach(AbstractDay<out Any>::run)
}