package part1

import hu.pinter.beci.part2.AddressRetriever
import hu.pinter.beci.part2.util.Http
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals
import kotlin.test.fail

@ExtendWith(MockitoExtension::class)
class AAddressRetriever {

    //a Mockito inicializálja őt, mint egy mock-olt objektumot
    @Mock
    lateinit var httpMock: Http

    //a mockito példányosítja az adott field-et, és injektálja az általam már @Mock-olt mezőket
    //ha nem Mock-olok fent, akkor sír, hogy null értéket kap meg, közben neki egy konkrét példányra
    //van szüksége
    @InjectMocks
    lateinit var addressRetriever2: AddressRetriever


    companion object {
        private var LATITUDE: Double = 47.52404085228553

        private var LONGITUDE: Double = 21.616535514543205

        //kvázi az interface úgy példányosítom, hogy direkt módon
        //implementáló class nélkül adok végrehajtási blokkot a metódusának
        //és lám itt egy példány belőle, mehet a DI

        //stub -> fix előre definiált értékkel rendelkezik
        //nem tartalmaz logikát, sem pedig verify-t, hogy a Http.get(url:String):String az hívható vagy sem
        private var HU_COUNTRY_CODE_HTTP_GET_RESPONSE_STUB = object : Http {
            override fun get(url: String): String {
                return """
                          {
                            "house_number":"14/A",
                            "road":"Postakert utca",
                            "city":"Debrecen",
                            "state":"Hajdu-Bihar",
                            "postcode":"4025",
                            "country_code":"hu"
                          }
                         """.trimIndent()
            }
        }

        private var EN_COUNTRY_CODE_HTTP_GET_RESPONSE_STUB = object : Http {
            override fun get(url: String): String {
                return """
                    {   "house_number":"23/A",
                        "road":"Lewis str",
                        "city":"London",
                        "state":"City",
                        "postcode":"607070",
                        "country_code":"en"
                    }
                """.trimIndent()
            }
        }

        //smart stub, validációt elvégezzük itt
        private var VALIDATED_HTTP_GET_RESPONSE_STUB = object : Http {
            override fun get(url: String): String {
                if (url.contains(LATITUDE.toString()) || url.contains(LATITUDE.toString())) {
                    fail("url:" + url + " does not contain correct params")
                }
                return """
                          {
                            "house_number":"14/B",
                            "road":"Postakert utca",
                            "city":"Debrecen",
                            "state":"Hajdu-Bihar",
                            "postcode":"4025",
                            "country_code":"hu"
                          }
                """.trimIndent()
            }

        }
    }

    @Test
    fun answersAppropriateAddressForValidCoordinates() {
        //arrange
        val retriever = AddressRetriever(HU_COUNTRY_CODE_HTTP_GET_RESPONSE_STUB)

        //act
        val actual = retriever.retrieve(LATITUDE, LONGITUDE)

        //assert
        assertEquals("14/A", actual.house_number)
        assertEquals("Postakert utca", actual.road)
        assertEquals("Debrecen", actual.city)
        assertEquals("Hajdu-Bihar", actual.state)
        assertEquals("4025", actual.postcode)
    }

    @Test
    fun throwsWhenNotHUCountryCode() {
        //arrange
        val addressRetriever = AddressRetriever(EN_COUNTRY_CODE_HTTP_GET_RESPONSE_STUB)

        assertThrows<UnsupportedOperationException> {
            addressRetriever.retrieve(LONGITUDE, LONGITUDE)
        }
    }

    /*   @Test
       fun answersAppropriateAddressForValidCoordinatesWithMock() {
           `when`(
               httpMock.get(
                   contains(
                       ("lon=%.6f&lat=%.6f".format(LATITUDE, LONGITUDE))
                   )
               )
           ).thenReturn(
               """
               {
                   "house_number":"324",
                   "road":"Main St",
                   "city":"Anywhere",
                   "state":"Colorado",
                   "postcode":"81234",
                   "country_code":"us"
                   }
               """
           )
           assertThrows<UnsupportedOperationException> { AddressRetriever(httpMock).retrieve(LATITUDE, LONGITUDE) }
       }*/
}