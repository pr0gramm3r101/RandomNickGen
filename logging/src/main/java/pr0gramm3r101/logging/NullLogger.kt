package pr0gramm3r101.logging

import java.io.File
import java.util.function.Consumer

class NullLogger: Logger(File("/dev/null"), false, false) {
    override fun log(level: Loglevel, message: String) {}
    override fun log(level: Loglevel, message: String, displayMessage: token) {}
    override fun log(level: Loglevel, message: String, displayMessage: String) {}

    override fun setDefaultStreamLoglevel(level: Loglevel) {}

    override fun setLogCallback(callback: Consumer<String>?) {}

    override fun setLogCallbackEnabled(enabled: Boolean) {}

    override fun setStreamMsg(msg: token?) {}

    override fun disableLogCallback() {}

    override fun enableLogCallback() {}

    override fun getLogCallback() = null

    override fun isLogCallbackEnabled() = false
}