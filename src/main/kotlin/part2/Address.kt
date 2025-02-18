package hu.pinter.beci.part2

import kotlinx.serialization.Serializable

@Serializable
class Address(
    val road: String,
    val city: String,
    val state: String,
    val country_code: String,
    val house_number: String,
    val postcode: String
)