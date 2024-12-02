package days

import AbstractDay
import kotlin.math.absoluteValue

class Day1: AbstractDay<Int>("01", 2024, "Historian Hysteria") {
    override fun part1(input: List<String>): Int {
        val lefty: MutableList<Int> = mutableListOf()
        val righty: MutableList<Int>  = mutableListOf()
        input.map {
            it.split("   ")
        }.forEach {
            lefty.add(it.first().toInt())
            righty.add(it.last().toInt())
        }

        lefty.sort()
        righty.sort()
        return lefty.mapIndexed { index, it ->
            (it - righty[index]).absoluteValue
        }.reduce<Int, Int>(Int::plus)
    }

    override fun part2(input: List<String>): Int {
        val lefty: MutableList<Int> = mutableListOf()
        val righty: MutableList<Int>  = mutableListOf()
        input.map {
            it.split("   ")
        }.map {
            lefty.add(it.first().toInt())
            righty.add(it.last().toInt())
        }


        return lefty.map {
            it * righty.filter { i -> i == it }.size
        }.reduce<Int, Int>(Int::plus)
    }
}