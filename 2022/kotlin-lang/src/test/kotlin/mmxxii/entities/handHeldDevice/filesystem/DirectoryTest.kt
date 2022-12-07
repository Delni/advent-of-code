package mmxxii.entities.handHeldDevice.filesystem

import org.junit.jupiter.api.Test
import kotlin.test.expect

class DirectoryTest {
    @Test
    fun `should flatten deep directories`() {
        val fileTree = Directory(
            name = "/",
            directories = mutableListOf(
                Directory(
                    name = "a", directories = mutableListOf(
                        Directory(
                            name = "b", directories = mutableListOf(
                                Directory(name = "c")
                            )
                        )
                    )
                )
            )
        )

        expect(listOf("c", "b", "a", "/")) {
            fileTree.flatten().map(Directory::name)
        }
    }

}