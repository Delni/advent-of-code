package mmxxii.entities


typealias Tree = Int

infix fun Tree.canSeeFarWithTrees(list: List<Tree>): Int {
    val firstIndex = list.indexOfFirst { it >= this }
    if (firstIndex == -1 || list.size == 1) {
        return list.size
    }
    return list.take(firstIndex + 1).size
}

fun List<String>.toTrees() = map {
    it
        .split("")
        .filter(String::isNotBlank)
        .map(String::toInt)
}