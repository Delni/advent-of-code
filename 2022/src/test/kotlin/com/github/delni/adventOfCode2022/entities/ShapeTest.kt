package com.github.delni.adventOfCode2022.entities

import kotlin.test.Test
import kotlin.test.assertTrue


class ShapeTest {
    @Test
    fun `it should win according to shape`() {
        val rock = Rock()
        val paper = Paper()
        val scissor = Scissors()
        assertTrue { rock winAgainst scissor }
        assertTrue { rock drawAgainst rock }
        assertTrue { rock loseAgainst paper }
        assertTrue { paper loseAgainst scissor }
        assertTrue { paper drawAgainst paper }
        assertTrue { scissor drawAgainst scissor }
    }
}