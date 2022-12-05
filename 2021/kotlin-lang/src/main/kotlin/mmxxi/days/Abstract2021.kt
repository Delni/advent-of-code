package mmxxi.days

import AbstractDay

abstract class Abstract2021(
    day: String,
    title: String,
    testValue1: Int? = null,
    testValue2: Int? = null
): AbstractDay<Int>(day, 2021, title, testValue1, testValue2) {
    init { runWithTest() }
}