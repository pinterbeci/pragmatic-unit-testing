package part1

import hu.pinter.beci.part1.DestinationList
import hu.pinter.beci.part1.Location
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ADestinationList {
    lateinit var destinationList: DestinationList

    companion object {
        var LOCATION_ONE = Location.of(1, 1, Location.Heading.East)
        var LOCATION_TWO = Location.of(211, -9801, Location.Heading.North)
    }

    @BeforeEach
    fun setUp() {
        destinationList = DestinationList()
    }

    @Test
    fun isEmptyWhenCreated() {
        assertTrue(destinationList.getLocations().isEmpty())
    }


    fun allowsAddingLocation() {
        //arrange --> fixálva van a két instance, amit
        //az 'act' fázisban hozzá kívánunk adni, majd az 'assert' fázisban
        // megvizsgáljuk, hogy tényleg sikeres volt-e a hozzáadás
        val locationOne = Location.of(1, 1, Location.Heading.East)
        val locationTwo = Location.of(211, -9801, Location.Heading.North)

        //act
        destinationList.add(
            locationOne
        )
        destinationList.add(
            locationTwo
        )

        //assert
        assertEquals(
            listOf(locationOne, locationTwo),
            destinationList.getLocations()
        )
    }

    @Test
    fun allowsAddingLocations() {
        //act
        destinationList.add(LOCATION_ONE)
        destinationList.add(LOCATION_TWO)

        //assert
        assertEquals(
            listOf(LOCATION_ONE, LOCATION_TWO),
            destinationList.getLocations()
        )
    }

    //mivel le lett kezelve, hogy mi történjen, ha ugyanazt az elemet szeretném hozzáadni
    //így nem történik duplikáció
    @Test
    fun doesNotAddLocationAlreadyContained() {
        destinationList.add(LOCATION_ONE)
        destinationList.add(LOCATION_TWO)

        destinationList.add(LOCATION_ONE)

        assertEquals(
            listOf(LOCATION_ONE, LOCATION_TWO),
            destinationList.getLocations()
        )
    }
}