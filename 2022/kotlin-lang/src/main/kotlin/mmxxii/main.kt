package mmxxii

import mmxxii.days.*

fun main() {
    listOf(
        Day1(),
        Day2(),
        Day3(),
        Day4(),
        Day5(),
        Day6(),
        Day7(),
        Day8()
    ).forEach(Abstract2022<out Any>::run)
}
