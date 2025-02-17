package part1

import hu.pinter.beci.part1.Location
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class ALocation {

    companion object {
        var HEADING: Location.Heading = Location.Heading.North
    }

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

    @Test
    fun isOWhenCoordinatesAreSame() {
        assertEquals(0.0, Location.of(1, 2, HEADING).distanceFrom(1, 2))
    }

    //Hypotenuse == átfogó, kvázi egy általános teszt, ami nem 0
    @Test
    fun is5InClassicHypotenuseCase() {
        assertEquals(5.0, Location.of(0, 0, HEADING).distanceFrom(3, 4))
    }

    //absouluteTolerance -> ∣expected−actual∣ ≤ absoluteTolerance
    @Test
    fun isNearSomeDoubleValue() {
        assertEquals(
            5.6568, Location.of(10, 13, HEADING).distanceFrom(14, 9),
            0.0001
        )
    }

    @Test
    fun worksWithNegativeNumbers() {
        assertEquals(
            23.7697,
            Location.of(-7, 13, HEADING).distanceFrom(2, -9),
            0.0001
        )
    }
}