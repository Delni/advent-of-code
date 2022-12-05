package mmxxii.entities

class GiantCargoCrane(val stacks: List<MutableList<Char>>, val moves: List<CraneMove>) {
    fun execute9000(): GiantCargoCrane = execute(List<Char>::reversed)

    fun execute9001(): GiantCargoCrane = execute()

    private fun execute(transform: ((List<Char>) -> Iterable<Char>)? = null) = apply {
        moves.forEach {
            val fromStack = stacks[it.start - 1]
            val movingCrates = fromStack.takeLast(it.amount).let {stack ->
                transform?.let { t -> t(stack) } ?: stack
            }
            stacks[it.end - 1].addAll(movingCrates)
            for (i in 0..(it.amount - 1)) {
                fromStack.removeLast()
            }
        }
    }

    val top: List<Char>
        get() = stacks.map { it.last() }

    override fun toString(): String {
        return stacks.mapIndexed { index, chars ->
            "$index : ${chars.joinToString()}"
        }.joinToString("\n")
    }
}

class CraneMove(val amount: Int, val start: Int, private var endStack: Int = -1) {
    val end: Int
        get() = endStack

    infix fun onto(end: Int): CraneMove {
        endStack = end
        return this
    }

    override fun equals(other: Any?): Boolean {
        return other is CraneMove && other.hashCode() == hashCode()
    }

    override fun hashCode(): Int {
        return amount + start + end
    }

    override fun toString() = "Move: $amount from $start to $end"
}

infix fun Int.from(start: Int) = CraneMove(this, start)

fun List<String>.toGiantCargoCrane(): GiantCargoCrane {
    val parsedInput = joinToString("\n").split("\n\n")
    val stacks = parsedInput.first().split("\n").reversed().run {
        val finalStacks = mutableListOf<MutableList<Char>>()
        val stackLength = Regex("""\d""").findAll(first()).toList().size - 1
        for (i in 0..stackLength) {
            val stackOffset = first().indexOf("${i + 1}")
            drop(1).map { it.elementAt(stackOffset) }
                .filter { it != ' ' }
                .let { finalStacks.add(i, it.toMutableList()) }

        }
        finalStacks
    }
    val moves = parsedInput.last().split("\n").map {
        val ordinals = Regex("""move (\d+) from (\d) to (\d)""")
            .findAll(it)
            .first()
            .groups
            .drop(1)
            .filterNotNull()
            .map(MatchGroup::value)
            .map(String::toInt)
        ordinals[0] from ordinals[1] onto ordinals[2]
    }

    return GiantCargoCrane(
        stacks = stacks,
        moves = moves
    )
}