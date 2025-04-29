package pr0gramm3r101.randomnickgen

import pr0gramm3r101.logging.Logger
import pr0gramm3r101.logging.Loglevel
import pr0gramm3r101.logging.NullLogger
import pr0gramm3r101.randomnickgen.apis.MinecraftAPI
import pr0gramm3r101.randomnickgen.apis.WordAPI
import pr0gramm3r101.util.io.FileUtil
import java.io.IOException
import java.util.*
import kotlin.random.Random


// Global constants
private val characterNumberMappings: Map<Char, Char> = mapOf(
    'o' to '0',
    'i' to '1',
    'l' to '1',
    'f' to '4',
    'a' to '4',
    's' to '5',
    'e' to '3'
)

private val prefixes = arrayOf(
    "the",
    "get",
    "not",
    "great",
    "good",
    "bad"
)

private val suffixes = arrayOf(
    "lol",
    "xd",
    "error",
    "4k",
    "2k",
    "ed"
)

// Arguments
private var quiet = false
private var forceTruncate = false
private var noExistenceCheck = false
private var count: Int? = null
private var noLog = false
private var save = false

// Saved nicknames
private var generatedNicknames = ""
private var nicknamesGenerated = 0

// Logger
lateinit var logger: Logger

fun _generateNickname(forceTruncate: Boolean = false, noExistenceCheck: Boolean = false): String? {
    logger.log(Loglevel.INFO, "Starting RandomNickGen, made by denis0001-dev")

    logger.log(Loglevel.INFO, "Generating a random word")
    var name = WordAPI.randomWord()
    logger.log(Loglevel.DEBUG, "Random word generated: $name")

    // Randomly add prefixes and suffixes
    logger.log(Loglevel.INFO, "Adding random prefixes & suffixes...")
    if (Math.random() < 0.2) {
        var prefix = prefixes.random()
        if (Random.nextBoolean()) {
            prefix += "_"
        }

        name = prefix + name
    }
    if (Math.random() < 0.2) {
        var suffix = suffixes.random()
        if (Random.nextBoolean()) {
            suffix = "_$suffix"
        }

        name += suffix
    }
    logger.log(Loglevel.DEBUG, "Result: $name")

    var nameArray = name.toCharArray()

    // Randomly change letters to numbers
    logger.log(Loglevel.INFO, "Randomly changing letters to numbers...")
    for (i in nameArray.indices) {
        if (Math.random() < 0.2) {
            characterNumberMappings[nameArray[i]]?.let {
                nameArray[i] = it
            }
        }
    }
    name = String(nameArray)
    logger.log(Loglevel.DEBUG, "Result: $name")

    // Randomly add underscores
    logger.log(Loglevel.INFO, "Randomly adding underscores...")
    if (Math.random() < 0.3) {
        name = "_$name"
    }
    if (Math.random() < 0.3) {
        name += "_"
    }
    logger.log(Loglevel.DEBUG, "Result: $name")

    // Random capitalization
    logger.log(Loglevel.INFO, "Adding random capitalization...")
    if (Random.nextBoolean()) {
        name = name.replaceFirstChar { it.uppercase() }
    } else if (Math.random() < 0.1) {
        name = name.uppercase(Locale.getDefault())
    } else if (Math.random() < 0.2) {
        nameArray = name.toCharArray()

        for (i in nameArray.indices) {
            if (Random.nextBoolean()) {
                nameArray[i] = nameArray[i].uppercaseChar()
            }
        }
        name = String(nameArray)
    }
    logger.log(Loglevel.DEBUG, "Result: $name")

    // Check if the name is 3-16 characters long
    logger.log(Loglevel.INFO, "Checking the name")
    if (name.length > 16) {
        if (forceTruncate) {
            logger.log(Loglevel.WARN, "The name was too long, truncated to first 16 characters")
        } else {
            logger.log(Loglevel.WARN, "The name is too long, retrying...")
            return null
        }
    }
    if (name.length < 3) {
        logger.log(Loglevel.WARN, "The name is too short, retrying...")
        return null
    }
    if (!noExistenceCheck) {
        if (MinecraftAPI.usernameExists(name)) {
            logger.log(Loglevel.WARN, "The name is taken, retrying...")
            return null
        }
    } else {
        logger.log(Loglevel.WARN, "The name is taken, but still returned because of --no-existence-check")
    }
    logger.log(Loglevel.INFO, "The name is OK!")
    return name
}

fun generateNickname(forceTruncate: Boolean = false, noExistenceCheck: Boolean = false): String? {
    val result = _generateNickname(forceTruncate, noExistenceCheck)
    if (save) {
        generatedNicknames += " $result"
        if (nicknamesGenerated >= 1000) {
            FileUtil.writeToFile(FileUtil.token.APPEND, "./.generated_nicks", generatedNicknames)
            nicknamesGenerated = 0
            generatedNicknames = ""
        }
    }
    return result
}

@Throws(IOException::class)
fun main(args: Array<String>) {
    var prevArg: String? = null

    for (it in args) {
        when (prevArg) {
            "--count" -> count = it.toInt()
        }
        when (it) {
            "--quiet", "-q" -> quiet = true
            "--force-truncate", "-t" -> forceTruncate = true
            "--no-existence-check", "-c" -> noExistenceCheck = true
            "--no-log" -> noLog = true
            "--save", "-s" -> save = true
        }
        prevArg = it
    }

    logger = if (noLog) NullLogger() else Logger("randomnickgen.log", !quiet, !quiet)
    logger.log(Loglevel.INFO, "Starting")
    logger.log(
        Loglevel.DEBUG,
"Arguments: quiet = $quiet, " +
        "forceTruncate = $forceTruncate, " +
        "noExistenceCheck = $noExistenceCheck, " +
        "count = $count"
    )

    if (count != null) {
        for (i in 0..count!!) {
            val name = run {
                while (true) {
                    return@run generateNickname(
                        forceTruncate = forceTruncate,
                        noExistenceCheck = noExistenceCheck
                    ) ?: continue
                }
            }
            if (quiet) println(name)
            logger.log(Loglevel.INFO, "Generated nickname: $name")
        }
    } else {
        while (true) {
            val name = generateNickname(
                forceTruncate = forceTruncate,
                noExistenceCheck = noExistenceCheck
            ) ?: continue
            logger.log(Loglevel.INFO, "Generated nickname: $name")
            if (quiet) {
                println(name)
                break
            }
            else {
                print("Retry? (y/n)? ")
                val choice = readln()
                if (!choice.equals("y", ignoreCase = true)) break
            }
        }
    }
    if (save) {
        FileUtil.writeToFile(FileUtil.token.APPEND, "./.generated_nicks", generatedNicknames)
    }
}