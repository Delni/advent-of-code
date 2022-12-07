package mmxxii.days

import mmxxii.entities.handHeldDevice.filesystem.Directory
import mmxxii.entities.handHeldDevice.filesystem.ElfFile

class Day7 : Abstract2022<Int>("07", "No Space Left On Device") {
    override fun part1(input: List<String>) = read(input).flatten()
        .map(Directory::size)
        .filter { it <= 100000 }
        .sum()

    override fun part2(input: List<String>) = with(read(input)) {
        val unusedSpace = Directory.totalDiskSpace - size
        flatten()
            .map(Directory::size)
            .filter { it > Directory.updateNeeds - unusedSpace }.minOf { it }
    }

    fun read(input: List<String>): Directory {
        val root = Directory("/")
        var cwd = root
        input.forEach {
            Regex("""^\$ cd (.+)""")
                .find(it)
                ?.groupValues
                ?.get(1)
                ?.let { dirname ->
                    cwd = when (dirname) {
                        "/" -> cwd.goToRoot()
                        ".." -> cwd.parent!!
                        else -> cwd.changeDirectory(dirname)
                    }
                }

            Regex("""^dir (.+)""")
                .find(it)
                ?.groupValues
                ?.get(1)
                ?.also { dirName ->
                    cwd.directories.add(Directory(dirName, cwd))
                }

            Regex("""^(\d+) (.+)""")
                .find(it)
                ?.groupValues
                ?.run {
                    cwd.files.add(ElfFile(name = get(2), size = get(1).toInt()))
                }

        }
        return root
    }
}