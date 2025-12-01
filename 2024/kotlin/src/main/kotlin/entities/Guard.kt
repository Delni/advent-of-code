package entities

class Guard(val origin: Point2D, var direction: Direction = Direction.UP) {
    var position: Point2D = origin
        private set

    enum class Direction(val vector: Vector2D) {
        UP(Vector2D(0, -1)),
        RIGHT(Vector2D(1, 0)),
        DOWN(Vector2D(0, 1)),
        LEFT(Vector2D(-1, 0)),
    }

    infix fun inside(grid: Grid2D) = position inside grid

    fun moveOn(area: Grid2D) {
        when (area.getValueOf(position+direction.vector)) {
            "#" -> turnRight()
            else -> moveForward()
        }
    }

    private fun turnRight() {
        direction = when (direction) {
            Direction.UP -> Direction.RIGHT
            Direction.RIGHT -> Direction.DOWN
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.UP
        }
    }

    private fun moveForward() {
        position +=direction.vector
    }

}