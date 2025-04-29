package pr0gramm3r101.randomnickgen.apis

object WordAPI {
    private val words = run {
        val list = mutableListOf<String>()
        var currentWord = ""
        this::class.java.getResourceAsStream("/words.txt")!!.bufferedReader().use {
            while (true) {
                when (val char = it.read()) {
                    ' '.code -> {
                        list += currentWord
                        currentWord = ""
                    }

                    -1 -> return@use
                    else -> currentWord += char.toChar()
                }
            }
        }
        list
    }

    fun randomWord() = words.random()
}