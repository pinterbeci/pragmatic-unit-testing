package hu.pinter.beci.part1

class StringUtils {
    companion object {
        fun capitalize(word: String): String {

            if (word.isEmpty()) return ""

            val head = word.substring(0, 1)
            val tail = word.substring(1)
            return head.uppercase() + tail.lowercase()
        }
    }
}