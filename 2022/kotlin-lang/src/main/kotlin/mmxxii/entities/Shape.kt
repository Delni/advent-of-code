package mmxxii.entities

private const val LOST = 0
private const val DRAW = 3
private const val WIN = 6

sealed class Shape(val points: Int) {
    abstract infix fun against(other: Shape): Int
    infix fun winAgainst(other: Shape) = this against other == WIN
    infix fun drawAgainst(other: Shape) = this against other == DRAW
    infix fun loseAgainst(other: Shape) = this against other == LOST

    companion object {
        val allShapes = listOf(Rock(), Paper(), Scissors())
    }
}

class Paper : Shape(2) {
    override fun against(other: Shape) = when (other) {
        is Paper -> DRAW
        is Rock -> WIN
        is Scissors -> LOST
    }
}

class Rock : Shape(1) {
    override fun against(other: Shape) = when (other) {
        is Paper -> LOST
        is Rock -> DRAW
        is Scissors -> WIN
    }
}

class Scissors : Shape(3) {
    override fun against(other: Shape) = when (other) {
        is Paper -> WIN
        is Rock -> LOST
        is Scissors -> DRAW
    }
}