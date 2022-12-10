package mmxxii.entities.handHeldDevice.hardware

class CathodicRayTube {
    private var registerX = 1
    val rom = mutableListOf<Int>()
    val screen = List(6) { MutableList(40) { DARK_PIXEL } }

    fun compute(input: List<String>): CathodicRayTube = apply {
        val addXRegex = Regex("""addx (-)?(\d+)""")
        tick()
        input.forEach {
            tick()
            it
                .takeIf { it.matches(addXRegex) }
                ?.let { io ->
                    val sign = (addXRegex.find(io)!!.groupValues[1] + "1").toInt()
                    val x = addXRegex.find(io)!!.groupValues[2].toInt()
                    tick()
                    registerX += x * sign
                }
        }
    }

    private fun tick() {
        render()
        rom.add(registerX)
    }

    private fun render() {
        val pixel = rom.size - 1
        val column = pixel % 40
        val row = pixel / 40
        if (registerX in column - 1..column + 1) {
            screen[row][column] = LIT_PIXEL
        }
    }

    override fun toString() = screen.joinToString("\n") { it.joinToString("") }

    companion object {
        const val LIT_PIXEL = "█"
        const val DARK_PIXEL = " "
    }
}