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
            listOf(locationOne, locationTwo), destinationList.getLocations()
        )
    }

    @Test
    fun allowsAddingLocations() {
        //act
        destinationList.add(LOCATION_ONE)
        destinationList.add(LOCATION_TWO)

        //assert
        assertEquals(
            listOf(LOCATION_ONE, LOCATION_TWO), destinationList.getLocations()
        )
    }

    //mivel le lett kezelve, hogy mi történjen, ha ugyanazt az elemet szeretném hozzáadni
    //így nem történik duplikáció
    fun doesNotAddLocationAlreadyContained() {

        // merül fel a kérdés ez az act vagy arrrange step?
        //de mivel külön van szedve az arrange, azaz a tesztadat inicializálás
        //így könnyebben választ találhatok a kérdésre
        //az sokat segít, ha a teszt objektumokat elkülönítjük a tesztől magától,
        //szeparálva, ha nem a teszt tartalmazza, olvashatóbb a teszt
        destinationList.add(LOCATION_ONE)
        destinationList.add(LOCATION_TWO)

        destinationList.add(LOCATION_ONE)

        assertEquals(
            listOf(LOCATION_ONE, LOCATION_TWO), destinationList.getLocations()
        )
    }

    //az fontos, ha az 'act' stepben tesztelt metódus egy kondíciót tartalmaz vagy többet,
    //akkor arra is tesztet kell létrehozni, ha ez a kondíció false-szal van kiértékelve
    @Test
    fun updatesMatchingCoordinatesWithNewCoordinates() {
        //assert
        destinationList.add(LOCATION_TWO)
        destinationList.add(LOCATION_ONE)

        //act
        destinationList.moveLocationsWithHeading(110, -546, Location.Heading.East)

        //assert
        assertEquals(
            listOf(LOCATION_TWO, Location.of(110, -546, Location.Heading.East)),
            destinationList.getLocations()
        )
    }

    @Test
    fun retainsLocationsLessThanDistance() {
        //arrange
        val firstLocation = Location.of(0, 5, Location.Heading.North)
        val secondLocation = Location.of(0, 10, Location.Heading.North)
        val thirdLocation = Location.of(0, 15, Location.Heading.North)

        destinationList.add(firstLocation)
        destinationList.add(secondLocation)
        destinationList.add(thirdLocation)

        //act
        destinationList.removeLocationsFurtherThan(0, 0, 9)

        //asset
        assertEquals(listOf(firstLocation), destinationList.getLocations())

    }
}