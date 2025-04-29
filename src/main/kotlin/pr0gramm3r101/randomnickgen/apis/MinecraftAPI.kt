package pr0gramm3r101.randomnickgen.apis

import org.json.JSONObject
import pr0gramm3r101.logging.Loglevel
import pr0gramm3r101.randomnickgen.logger
import java.io.PrintWriter
import java.io.StringWriter
import java.net.HttpURLConnection
import java.net.URI

object MinecraftAPI {
    private const val URL: String = "https://api.mojang.com/users/profiles/minecraft"

    fun usernameExists(username: String): Boolean {
        try {
            val obj = URI("$URL/$username").toURL()
            val con = obj.openConnection() as HttpURLConnection

            con.requestMethod = "GET"
            con.setRequestProperty("User-Agent", "Mozilla/5.0")
            con.connectTimeout = 5000
            con.readTimeout = 5000

            val response = try {
                con.inputStream.use { it.readAllBytes() }
            } catch (e: Exception) {
                con.errorStream.use { it.readAllBytes() }
            }

            logger.log(Loglevel.DEBUG, "Minecraft API response: ${String(response)}")

            return JSONObject(String(response)).has("name")
        } catch (e: Exception) {
            logger.log(Loglevel.ERROR, "Minecraft API connection failed.")
            val sw = StringWriter()
            val pw = PrintWriter(sw)
            e.printStackTrace(pw)
            logger.log(Loglevel.ERROR, sw.toString())
            return false
        }
    }
}