package mmxxii.days

import AbstractDay

abstract class Abstract2022(
    day: String,
    title: String,
    testValue1: Int? = null,
    testValue2: Int? = null
): AbstractDay(day, 2022, title, testValue1, testValue2)