package days

import AbstractDay

class Day5 : AbstractDay<Int>("05", 2024, "Print Queue") {

    override fun part1(input: List<String>) = with(input.toPrintQueue()) {
        getCorrectlyOrderedManuals()
            .map { pages -> pages[pages.size / 2] }
            .sumOf { it }
    }

    override fun part2(input: List<String>) = with(input.toPrintQueue()) {
        getIncorrectlyOrderedManuals()
            .map { pages -> pages[pages.size / 2] }
            .sumOf { it }
    }


    fun List<String>.toPrintQueue() = PrintQueue(
        orderingRule = takeWhile<String>(String::isNotEmpty)
            .map { it.split("|").map(String::toInt) }
            .fold(mutableMapOf<Int, MutableList<Int>>()) { map, it ->
                val x = it.first()
                val y = it.last()
                val value = map.getOrDefault(x, mutableListOf())
                value.add(y)
                map.apply { put(x, value) }
            },
        safetyManuals = takeLastWhile<String>(String::isNotEmpty).map {
            it.split(",").map(String::toInt)
        }
    )
}

class PrintQueue(val orderingRule: Map<Int, List<Int>>, val safetyManuals: List<List<Int>>) {
    fun getCorrectlyOrderedManuals() = safetyManuals.filter { pages ->
        pages.all { page ->
            !orderingRule.containsKey(page) || orderingRule[page]!!
                .all { subsequentPage ->
                    pages.indexOf(subsequentPage) > pages.indexOf(page)
                            || pages.indexOf(subsequentPage) == -1
                }
        }

    }

    fun getIncorrectlyOrderedManuals() = safetyManuals.filter { pages ->
        !pages.all { page ->
            !orderingRule.containsKey(page) || orderingRule[page]!!
                .all { subsequentPage ->
                    pages.indexOf(subsequentPage) > pages.indexOf(page)
                            || pages.indexOf(subsequentPage) == -1
                }
        }

    }

    /*fun List<List<Int>>.reorder() = map { pages ->
        val result = mutableListOf<Int>()
        val remaining = pages.map { it }.toMutableList()

        fun findParent(page: Int): Int {
            val parent: Int = orderingRule
                .filterValues { it.contains(page) }
                .keys
                .firstOrNull()
                ?: page

            return if (parent != page) findParent(parent) else page
        }
        pages.forEach { page ->
            val parent = findParent(page)
            if (!result.contains(parent)) {
                result.add(parent)
            } else {
                result.add(page)
            }
        }
        result
    }*/
}