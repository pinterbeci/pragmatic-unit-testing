package hu.pinter.beci.part1

class Location private constructor(
    val x: Int,
    val y: Int,
    val heading: Heading
) {
    enum class Heading {
        North, East, South, West
    }

    companion object {
        fun of(x: Int, y: Int, heading: Heading): Location {
            return Location(x, y, heading)
        }
    }

    fun move(distance: Int): Location {
        when (this.heading) {
            Heading.East -> return of(this.x + distance, this.y, heading)
            Heading.West -> return of(this.x - distance, this.y, heading)
            Heading.North -> return of(this.x, this.y + distance, this.heading)
            Heading.South -> return of(this.x, this.y - distance, this.heading)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Location

        if (x != other.x) return false
        if (y != other.y) return false
        if (heading != other.heading) return false

        return true
    }

    override fun hashCode(): Int = this::class.hashCode()


}