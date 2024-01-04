import java.io.File
import java.nio.file.FileAlreadyExistsException

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Usage: CopyUtility <sourceFilePath> <destinationFilePath>")
        return
    }

    val sourcePath = args[0]
    val destinationPath = args[1]

    try {
        copyFile(sourcePath, destinationPath)
        println("File copied successfully.")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}

fun copyFile(sourcePath: String, destinationPath: String) {
    val sourceFile = File(sourcePath)
    val destinationFile = File(destinationPath)

    if (!sourceFile.exists()) {
        throw IllegalArgumentException("Source file does not exist.")
    }

    try {
        destinationFile.createNewFile()
    } catch (e: FileAlreadyExistsException) {
        throw IllegalArgumentException("Destination file already exists.")
    }

    sourceFile.inputStream().use { input ->
        destinationFile.outputStream().use { output ->
            input.copyTo(output)
        }
    }
}
