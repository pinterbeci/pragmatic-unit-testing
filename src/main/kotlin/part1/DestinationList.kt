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
    fun getLocations(): List<Location> = locations

    fun moveLocationsWithHeading(x: Int, y: Int, heading: Location.Heading): MutableList<() -> Location> {
        return getLocations()
            .stream()
            .map { location ->
                {
                    if (location.heading == heading)
                        Location.of(x, y, heading)
                    location
                }
            }.toList()
    }
}