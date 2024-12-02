import days.Day1
import days.Day2

fun main() {
    listOf(
        Day1(),
        Day2(),
    ).forEach(AbstractDay<out Any>::run)
}