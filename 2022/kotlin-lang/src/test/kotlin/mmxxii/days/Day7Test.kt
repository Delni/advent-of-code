package mmxxii.days

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

import kotlin.test.expect

class Day7Test {

    private val testInput = listOf(
        "$ cd /",
        "$ ls",
        "dir a",
        "14848514 b.txt",
        "8504156 c.dat",
        "dir d",
        "$ cd a",
        "$ ls",
        "dir e",
        "29116 f",
        "2557 g",
        "62596 h.lst",
        "$ cd e",
        "$ ls",
        "584 i",
        "$ cd ..",
        "$ cd ..",
        "$ cd d",
        "$ ls",
        "4060174 j",
        "8033020 d.log",
        "5626152 d.ext",
        "7214296 k",
    )

    @Test
    fun `should read input`() {
        val filetree = Day7().read(testInput)
        expect(2) {
            filetree.directories.size
        }
        expect(2) {
            filetree.files.size
        }
        expect(3) {
            filetree.directories.first().files.size
        }
    }

    @Test
    fun `part 1 should return 95437 on test input`() = expect(95437) {
        Day7().part1(testInput)
    }

    @Test
    fun `part 2 should return 24933642 on test input`() = expect(24933642) {
        Day7().part2(testInput)
    }

    @Nested
    inner class OnProdInputs {
        private val input = File("../resources", "Day07.txt").readLines()

        @Test
        fun `part 1 should return 1770595 on test input`() = expect(1770595) {
            Day7().part1(input)
        }

        @Test
        fun `part 2 should return 2195372 on test input`() = expect(2195372) {
            Day7().part2(input)
        }
    }
}