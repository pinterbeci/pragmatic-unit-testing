package hu.pinter.beci.part2.util

interface Http {
    fun get(url: String) : String
}