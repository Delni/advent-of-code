package mmxxii.days

class Day8 : Abstract2022<Int>("08", "Treetop Tree House") {
    override fun part1(input: List<String>) = input
        .toListOfInt()
        .flatMapIndexed { i, trees ->
            trees.mapIndexed { j, tree ->
                (i to j).withDirections(trees, input) { left, right, top, bottom ->
                    listOf(
                        left.takeWhile { it < tree }.size == j,
                        right.takeLastWhile { it < tree }.size == trees.size - j - 1,
                        top.takeWhile { it < tree }.size == i,
                        bottom.takeLastWhile { it < tree }.size == input.size - i - 1,
                    ).takeIf { it.any { visible -> visible } }?.let { (i to j) }
                }

            }.filterNotNull()
        }
        .size

    override fun part2(input: List<String>) = input
        .toListOfInt()
        .flatMapIndexed { i, trees ->
            trees.mapIndexed { j, tree ->
                (i to j).withDirections(trees, input) { left, right, top, bottom ->
                    listOf(
                        tree canSeeFarWithTrees left.reversed(),
                        tree canSeeFarWithTrees right,
                        tree canSeeFarWithTrees top.reversed(),
                        tree canSeeFarWithTrees bottom
                    ).fold(1, Int::times)
                }
            }
        }
        .max()

    private fun List<String>.toListOfInt() = map {
        it
            .split("")
            .filter(String::isNotBlank)
            .map(String::toInt)
    }

    private fun <T> Pair<Int, Int>.withDirections(
        row: List<Int>,
        columns: List<String>,
        transform: (left: List<Int>, right: List<Int>, top: List<Int>, bottom: List<Int>) -> T
    ): T {
        val column = columns.map { it.toCharArray()[second].toString().toInt() }

        return transform(
            row.subList(0, second),
            row.subList(second + 1, row.size),
            column.subList(0, first),
            column.subList(first + 1, columns.size)
        )
    }
}

infix fun Int.canSeeFarWithTrees(list: List<Int>): Int {
    val firstIndex = list.indexOfFirst { it >= this }
    if (firstIndex == -1 || list.size == 1) {
        return list.size
    }
    return list.take(firstIndex + 1).size
}

