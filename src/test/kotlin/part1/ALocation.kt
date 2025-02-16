package part1

import hu.pinter.beci.part1.Location
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ALocation {

    @Test
    fun increasesYCoordinateWhenMovingNorth() {
        //arrange
        val testLocation = Location.of(0, 0, Location.Heading.North)

        //act
        val actual = testLocation.move(42)

        /*
        assertEquals(0, actual.x)
        assertEquals(42, actual.y)
        assertEquals(Location.Heading.North, actual.heading)
        */
        //assert
        //ekvivalens a fenti három assert-tal
        assertEquals(Location.of(0, 42, Location.Heading.North), actual)
    }

    @Test
    fun increasesXCoordinateWhenMovingEast() {
        assertEquals(
            Location.of(-80, 0, Location.Heading.East),
            Location.of(-90, 0, Location.Heading.East).move(10)
        )
    }

    @Test
    fun decreaseYCoordinateWhenMovingSouth() {
        assertEquals(
            Location.of(-23, -80, Location.Heading.South),
            Location.of(-23, -70, Location.Heading.South).move(10)
        )
    }

    @Test
    fun decreaseXCoordinateWhenMovingWest() {
        assertEquals(
            Location.of(-43, 34, Location.Heading.West),
            Location.of(10, 34, Location.Heading.West).move(53)
        )
    }
}