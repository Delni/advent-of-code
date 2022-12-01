import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String, year: Int) = File("$year/src/main/resources", "$name.txt").readLines()
