package mmxxii.days

class Day18 : Abstract2022<Int>("18", "Boiling Boulders") {
    override fun part1(input: List<String>) = input.map(String::toPoint3D).let {
        it.map { droplet ->
            droplet.findNeighbors(it)
        }.sumOf { neighbors -> 6 - neighbors.size }
    }

    override fun part2(input: List<String>) = input.map(String::toPoint3D).size
}

data class Point3D(val x: Int, val y: Int, val z: Int) {
    fun findNeighbors(droplets: List<Point3D>): List<Point3D> = droplets.filter {
        it == Point3D(x + 1, y, z) ||
                it == Point3D(x - 1, y, z) ||
                it == Point3D(x, y + 1, z) ||
                it == Point3D(x, y - 1, z) ||
                it == Point3D(x, y, z + 1) ||
                it == Point3D(x, y, z - 1)
    }
}

fun String.toPoint3D() = split(",")
    .filter(String::isNotBlank)
    .map(String::toInt)
    .let { Point3D(it[0], it[1], it[2]) }