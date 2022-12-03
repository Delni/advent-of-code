import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String, year: Int) = File("$year/resources", "$name.txt").readLines()
