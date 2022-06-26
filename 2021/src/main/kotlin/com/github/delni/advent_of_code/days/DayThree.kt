package com.github.delni.advent_of_code.days

import com.github.delni.advent_of_code.submarine.Submarine

class DayThree: AbstractDay("03", "Binary Diagnostic", 198) {
    override fun part1(input: List<String>): Int {
        val sub = Submarine()
        sub.diagnose(input)

        return Integer.valueOf(sub.epsilon, 2) * Integer.valueOf(sub.gamma, 2)
    }

    override fun part2(input: List<String>): Int {
//        val sub = Submarine()
//        var subInput = input.toList()
//        var counter = 0
//        do {
//            sub.diagnose(subInput)
//            subInput = subInput.filter { it[counter] == sub.gamma[counter] }
//            counter++
//        } while (subInput.size != 1)
//        val oxGenerator = subInput.first()
//
//        subInput = input.toList()
//        counter = 0
//        do {
//            sub.diagnose(input)
//            subInput = subInput.filter { it[counter] == sub.epsilon[counter] }
//            counter++
//        } while (subInput.size != 1)
//        val co2Scrubber = subInput.first()
//
//
//        return Integer.valueOf(oxGenerator, 2) * Integer.valueOf(co2Scrubber, 2)
        return -1
    }
}