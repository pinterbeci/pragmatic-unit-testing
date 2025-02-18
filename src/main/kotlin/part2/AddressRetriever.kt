package hu.pinter.beci.part2

import hu.pinter.beci.part2.util.Http
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

//Dependency injection pattern - függőségnek való értékadás, nem az osztályon belül, hanem azon kívül
//Loose Coupling → The class does not depend on a specific implementation.
class AddressRetriever(private val http: Http) {

    companion object {
        private var SERVER: String = "https://nominatim.openstreetmap.org"
    }

    fun retrieve(latitude: Double, longitude: Double): Address {
        val locationParams = "lon=%.6f&lat=%.6f".format(latitude, longitude)
        val url = "%s/reverse?%s&format=json".format(SERVER, locationParams)
        val jsonResponse = http.get(url)

        val response = parseResponse(jsonResponse)
        val address = response.address
        val country = address.country_code
        if (country != "hu") {
            throw UnsupportedOperationException("intl addresses unsupported")
        }
        return address
    }

    private fun parseResponse(jsonResponse: String): Response {
        var addressResponse = Address("", "", "", "", "", "")
        try {
            addressResponse = Json.decodeFromString<Address>(jsonResponse)
        } catch (exception: Exception) {
            when (exception) {
                is IllegalStateException -> {
                    throw IllegalStateException("Not appropriate operation")
                }

                is SerializationException -> {
                    throw SerializationException("Serialization is not supported: json string to object")
                }
            }
        }
        return Response(addressResponse)
    }

}