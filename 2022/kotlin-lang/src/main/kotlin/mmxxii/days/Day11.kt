package mmxxii.days

import java.lang.Exception


class Day11 : Abstract2022<Long>("11", "Monkey in the Middle") {
    override fun part1(input: List<String>) = input
        .filter(String::isNotBlank)
        .windowed(6, 6)
        .map(List<String>::toMonkey)
        .also { it playRounds 20 }
        .findBusiness()

    override fun part2(input: List<String>) = input
        .filter(String::isNotBlank)
        .windowed(6, 6)
        .map(List<String>::toMonkey)
        .also { it playRoundsWithoutRelief 10000 }
        .findBusiness()

    private infix fun List<Monkey>.playRounds(rounds: Int) {
        for (i in 0 until rounds) {
            forEach {
                it.turnWithRelief { item, monkeyId ->
                    this[monkeyId].getItem(item)
                }
            }
        }
    }

    private infix fun List<Monkey>.playRoundsWithoutRelief(rounds: Int) {
        for (i in 0 until rounds) {
            forEach {
                it.turnWithoutRelief { item, monkeyId ->
                    get(monkeyId).getItem(item)
                }
            }
        }
    }

    private fun List<Monkey>.findBusiness(): Long = map { it.inspectedItems }
        .sortedDescending()
        .windowed(2)
        .first()
        .reduceRight(Long::times)
}

fun List<String>.toMonkey(): Monkey {
    if (size > 7) {
        throw Exception("Unable to parse $this")
    }

    val numberRegex = Regex("""\d+""")

    val inventory = numberRegex
        .findAll(get(1)).map {
            it.groupValues.first().toLong()
        }.toMutableList()

    val operation = Regex("""new = old (.) (\d+|old)""")
        .findAll(get(2)).map {
            val operators = it.groupValues.drop(1)

            fun(old: Long): Long {
                val member = when (operators.last()) {
                    "old" -> old
                    else -> operators.last().toLong()
                }
                return when (operators.first()) {
                    "*" -> old * member
                    "+" -> old + member
                    else -> throw NotImplementedError(operators.first())
                }
            }
        }.first()

    val test = subList(3, 6).mapNotNull {
        numberRegex.find(it)?.groupValues?.last()?.toInt()
    }.let {
        fun(item: Long): Int = when (item % it[0]) {
            0L -> it[1]
            else -> it[2]
        }
    }

    return Monkey(
        inventory = inventory,
        test = test,
        operation = operation
    )
}


data class Monkey(
    private val inventory: MutableList<Long>,
    val test: (Long) -> Int,
    val operation: (Long) -> Long
) {

    var inspectedItems: Long = 0L
    private fun Long.inspect(): Long = operation(this)
        .also { inspectedItems++ }

    fun turnWithRelief(block: (item: Long, monkeyId: Int) -> Unit) = turn(block) {
        div(3)
    }

    fun turnWithoutRelief(block: (item: Long, monkeyId: Int) -> Unit) = turn(block)

    private fun turn(block: (item: Long, monkeyId: Int) -> Unit, transform: Long.() -> Long = { this }) {
        while (inventory.isNotEmpty()) {
            val item = inventory
                .removeAt(0)
                .inspect()
                .transform()
            block(item, test(item))
        }
    }

    fun getItem(item: Long) {
        inventory.add(item)
    }
}
