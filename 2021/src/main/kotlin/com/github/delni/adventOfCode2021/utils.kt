package com.github.delni.adventOfCode2021

import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("2021/src/main/resources", "$name.txt").readLines()
