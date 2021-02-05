package wisefox.com.spaceodysseyserver.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*



/* *********
 * colors */

const val ANSI_RESET = "\u001B[0m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_RED = "\u001B[31m"


/* *************
 * printTrace */

//Info in server's console
fun traceServerRequest(tag: String) {
    val timeNow = Instant.now().toEpochMilli()
    println("${convertLongToTime(timeNow)} $ANSI_RED-- SERVEUR REQUEST --$ANSI_RESET $ANSI_GREEN $tag $ANSI_RESET")
}

//Convert timestamp to string for display
fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd-MM-yy HH:mm:ss")
    return format.format(date)
}