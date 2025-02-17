package hu.pinter.beci.part1

class DestinationList {
    private var locations: MutableList<Location> = ArrayList()

    fun add(location: Location) {
        //value object-ről beszélünk, azt pedig tudjunk mikor lesz egyenlő
        //ha összehasonlítunk kettőt, lásd equals()
        //ez miatt le kell kezelni, ha ugyanazt szeretném hozzáaadni
        if (locations.contains(location)) return

        locations.add(location)
    }

    //List<> read-only, így az olvasás okés, de fenti add csak MutableList<> típussal oldaható meg
    fun getLocations(): MutableList<Location> = locations

    fun moveLocationsWithHeading(x: Int, y: Int, heading: Location.Heading) {
        locations = getLocations().stream().map {
            if (it.heading == heading)
                Location.of(x, y, heading)
            else it
        }.toList()
    }

    fun removeLocationsFurtherThan(x: Int, y: Int, distance: Int) {
        locations = getLocations().stream().filter {
            it.distanceFrom(x, y) < distance
        }.toList()
    }

    //ez esetben ezen loginák a tesztelése is fontos lehet,
    //ezen osztály hatásköre a destination list management, így ez miatt el kell mozgatni ezt a metóudst

    /*private fun distanceBetween(point: Location, x: Int, y: Int): Double {
        return sqrt(
            (x - point.x).toDouble().pow(2.0) + (y - point.y).toDouble().pow(2.0)
        )
    }*/
}