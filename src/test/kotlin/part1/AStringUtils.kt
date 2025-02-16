package part1

import hu.pinter.beci.part1.StringUtils
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AStringUtils {

    //Zero-based test: A zero-based test can involve some
    //other form of nothingness: an empty array or a null value, for example
    @Test
    fun returnEmptyStringWhenEmpty() {
        val actual = StringUtils.capitalize("")

        assertEquals("", actual)
    }

    @Test
    fun uppercasesSingleLetter() {
        val actual = StringUtils.capitalize("a")
        assertEquals("A", actual)
    }

    @Test
    fun uppercasesFirstLetterOfLowercaseWord() {
        assertEquals("Alpha", StringUtils.capitalize("alpha"))
    }

    @Test
    fun lowercasesRemainderOfLetters() {
        assertEquals("Omega", StringUtils.capitalize("OMEGA"))
    }

    @Test
    fun capitalizeLetters() {
        assertEquals("Omega", StringUtils.capitalize("oMEGA"))
    }
}