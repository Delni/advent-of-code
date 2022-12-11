package mmxxii.entities

import java.lang.Exception

data class Monkey(
    private val inventory: MutableList<Long>,
    val modulo: Int,
    val test: (Long) -> Int,
    val operation: (Long) -> Long
) {

    var inspectedItems: Long = 0L
    private fun Long.inspect(): Long = operation(this)
        .also { inspectedItems++ }

    fun turnWithDiv(temper: Long, block: (item: Long, monkeyId: Int) -> Unit) = turn(block) {
        div(temper)
    }

    fun turnWithModulo(modulo: Long, block: (item: Long, monkeyId: Int) -> Unit) = turn(block) {
        mod(modulo)
    }

    private fun turn(block: (item: Long, monkeyId: Int) -> Unit, transform: Long.() -> Long = { this }) {
        while (inventory.isNotEmpty()) {
            val item = inventory
                .removeAt(0)
                .inspect()
                .transform()
            block(item, test(item))
        }
    }

    infix fun receive (item: Long) {
        inventory.add(item)
    }
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

    var modulo: Int
    val test = subList(3, 6).mapNotNull {
        numberRegex.find(it)?.groupValues?.last()?.toInt()
    }.let {
        modulo = it[0]
        fun(item: Long): Int = when (item % it[0]) {
            0L -> it[1]
            else -> it[2]
        }
    }

    return Monkey(
        inventory = inventory,
        modulo = modulo,
        test = test,
        operation = operation
    )
}