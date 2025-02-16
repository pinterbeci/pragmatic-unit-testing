package hu.pinter.beci.credit.history.first

import java.time.LocalDateTime

class Booking private constructor(
    val name: String,
    val age: Int,
    val departureDate: LocalDateTime,
    val itinerary: List<String>
) {
    private var errorMessages: ArrayList<String> = ArrayList()

    init {
        require(name.trim().isNotEmpty()) { errorMessages.add("Name is empty") }
        require(age >= 18) { errorMessages.add("Minor cannot fly unaccompanied") }
        require(departureDate.isAfter(LocalDateTime.now())) { errorMessages.add("Too late!") }
        require(itinerary.size >= 2) { errorMessages.add("Itinerary needs 2+ airport codes") }


        require(isValidItinerary()) { errorMessages.add("Itinerary contains invalid airport code") }
    }

    companion object {
        fun of(
            name: String, age: Int, departureDate: LocalDateTime, itinerary: List<String>
        ): Booking {
            return Booking(name, age, departureDate, itinerary)
        }
    }

    private fun isValidItinerary(): Boolean {
        return itinerary.stream()
            .map { item -> item.uppercase() }
            .allMatch { airportCode ->
                setOf("COS", "DEN", "DUB", "PRG").contains(airportCode)
            }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Booking

        if (age != other.age) return false
        if (name != other.name) return false
        if (departureDate != other.departureDate) return false
        if (itinerary != other.itinerary) return false

        return true
    }

    override fun hashCode(): Int = this::class.hashCode()
}