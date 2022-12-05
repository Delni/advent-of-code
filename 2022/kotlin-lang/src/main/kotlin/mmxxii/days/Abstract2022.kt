package mmxxii.days

import AbstractDay

abstract class Abstract2022<T>(
    day: String,
    title: String,
    testValue1: T? = null,
    testValue2: T? = null
): AbstractDay<T>(day, 2022, title, testValue1, testValue2)