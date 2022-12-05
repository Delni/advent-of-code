package mmxxii.days

import mmxxii.entities.toGiantCargoCrane

class Day5: Abstract2022<String>("05", "Supply Stacks") {
    override fun part1(input: List<String>) = input.toGiantCargoCrane().execute9000().top.joinToString("")

    override fun part2(input: List<String>) = input.toGiantCargoCrane().execute9001().top.joinToString("")
}