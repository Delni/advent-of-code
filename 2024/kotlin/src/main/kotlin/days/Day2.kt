package days

import AbstractDay
import kotlin.collections.map
import kotlin.math.absoluteValue

class Day2 : AbstractDay<Int>("02", 2024, "Red-Nosed Reports") {
    override fun part1(input: List<String>) = input
        .map(Report::from)
        .filter(Report::isSafe)
        .size


    override fun part2(input: List<String>) = input
        .map(Report::from)
        .filter(Report::isSafeWithProblemDampener)
        .size
}

data class Report(val levels: List<Int>) {
    val increasing: Boolean
        get() = levels.windowed(2).all { levels -> levels.first() < levels.last() }

    val decreasing: Boolean
        get() = levels.windowed(2).all { levels -> levels.first() > levels.last() }

    fun gapInBetween(min: Int, max: Int) = levels.windowed(2).all { levels ->
        (levels.first() - levels.last()).absoluteValue in (min + 1)..max
    }

    val isSafe: Boolean
        get() = (increasing || decreasing) && gapInBetween(0, 3)

    val isSafeWithProblemDampener: Boolean
        get() = isSafe || levels
            .mapIndexed { index, level -> levels.toMutableList().also { it.removeAt(index) } }
            .map(::Report)
            .any(Report::isSafe)

    companion object {
        fun from(string: String) = Report(string.split(" ").map { it.toInt() })
    }
}