package mmxxii.days

import mmxxii.entities.handHeldDevice.hardware.CathodicRayTube


class Day10 : Abstract2022<Int>("10", "Cathode-Ray Tube") {
    override fun part1(input: List<String>) = CathodicRayTube()
        .compute(input)
        .rom
        .mapIndexed { index, i ->
            index
                .takeIf { (it - 20) % 40 == 0 }
                ?.let { it * i }
        }
        .filterNotNull()
        .sum()

    override fun part2(input: List<String>) = CathodicRayTube()
        .compute(input)
        .apply { println(this) }
        .screen
        .flatten()
        .count { it == CathodicRayTube.LIT_PIXEL }
}

