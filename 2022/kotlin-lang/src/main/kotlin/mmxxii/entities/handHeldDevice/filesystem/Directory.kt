package mmxxii.entities.handHeldDevice.filesystem

data class Directory(
    val name: String,
    val parent: Directory? = null,
    val files: MutableList<ElfFile> = mutableListOf(),
    val directories: MutableList<Directory> = mutableListOf()
) {
    val size: Int
        get() = files.sumOf(ElfFile::size) + directories.sumOf(Directory::size)

    fun goToRoot(): Directory = parent?.goToRoot() ?: this
    fun changeDirectory(dirname: String): Directory =
        when (dirname) {
            "/" -> goToRoot()
            ".." -> parent!!
            else -> directories.find { it.name == dirname } ?: throw DirectoryNotFoundException(dirname)
        }

    fun flatten(): List<Directory> = directories
        .flatMap { it.directories.flatMap { d -> d.flatten() } }
        .toMutableList()
        .also { it.addAll(directories) }
        .also { it.add(this) }

    private class DirectoryNotFoundException(dirname: String) : Exception(dirname)
    companion object {
        const val totalDiskSpace = 70000000
        const val updateNeeds = 30000000
    }
}