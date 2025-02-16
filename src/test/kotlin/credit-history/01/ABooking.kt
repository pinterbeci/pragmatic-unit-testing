package `credit-history`.`01`

import hu.pinter.beci.`credit-history`.`01`.Booking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class ABooking {

    @Test
    fun createInvalidBookingByInvalidAirports() {

        assertThrows<IllegalArgumentException>("Invalid Booking, cause: 'Itinerary contains invalid airport code' ", {
            Booking.of(
                "To Tirana",
                25,
                LocalDateTime.of(2025, 4, 4, 10, 0, 0),
                listOf("BUD", "TIR")
            )
        })
    }

    @Test
    fun createInvalidBookingByAge() {
        assertThrows<IllegalArgumentException>("Invalid Booking, cause: 'Minor cannot fly unaccompanied' ", {
            Booking.of(
                "To Tirana",
                15,
                LocalDateTime.of(2025, 4, 4, 10, 0, 0),
                listOf("BUD", "TIR")
            )
        })
    }

    @Test
    fun createValidBooking() {
        assertDoesNotThrow {
            Booking.of(
                "Prague To Dubai",
                25,
                LocalDateTime.of(2025, 4, 4, 10, 0, 0),
                listOf("PRG", "DUB")
            )
        }
    }
}