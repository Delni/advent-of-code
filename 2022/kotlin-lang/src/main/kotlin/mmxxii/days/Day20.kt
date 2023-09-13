package mmxxii.days

import java.util.*
import kotlin.math.absoluteValue

class Day20 : Abstract2022<Int>("20", "Grove Positioning System") {
    override fun part1(input: List<String>) = input.map(String::toInt).let {
        val mixed = MutableList<Int?>(it.size) { null }
        it.forEachIndexed { index, value ->
            val newIndex = ((index + value) + it.size) % it.size
            mixed[newIndex]
                .takeIf { i -> i == null }
                ?.also { mixed.removeAt(newIndex) }
            mixed.add(newIndex, value)
        }
        mixed
    }.size

    override fun part2(input: List<String>) = input.size
}