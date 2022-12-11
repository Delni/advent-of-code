package mmxxii.days

import mmxxii.entities.Monkey
import mmxxii.entities.toMonkey


class Day11 : Abstract2022<Long>("11", "Monkey in the Middle") {
    override fun part1(input: List<String>) = input
        .toMonkeys()
        .also {
            it.playRounds(
                rounds = 20,
                temperedWith = 3,
                turn = Monkey::turnWithDiv
            )
        }
        .business

    override fun part2(input: List<String>) = input
        .toMonkeys()
        .also {
            it.playRounds(
                rounds = 10000,
                temperedWith = it.map(Monkey::modulo).reduceRight(Int::times).toLong(),
                turn = Monkey::turnWithModulo
            )
        }
        .business

    private fun List<String>.toMonkeys() = filter(String::isNotBlank)
        .windowed(6, 6)
        .map(List<String>::toMonkey)

    private fun List<Monkey>.playRounds(
        rounds: Int,
        temperedWith: Long,
        turn: Monkey.(temperWith: Long, block: (Long, Int) -> Unit) -> Unit
    ) {
        for (i in 0 until rounds) {
            forEach {
                turn.invoke(it, temperedWith) { item, monkeyId ->
                    get(monkeyId) receive item
                }
            }
        }
    }

    private val List<Monkey>.business: Long
        get() = map { it.inspectedItems }
            .sortedDescending()
            .windowed(2)
            .first()
            .reduceRight(Long::times)
}


