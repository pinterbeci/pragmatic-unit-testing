package hu.pinter.beci.part1

class CreditHistory {
    //nézz utána --> itt List<CreditRating> nem működik
    private val ratings: ArrayList<CreditRating> = ArrayList()
    fun add(credit: CreditRating) {
        ratings.add(credit)
    }

    fun arithmeticMean(): Int {
        if (ratings.isEmpty()) throw IllegalStateException()

        val total = ratings.stream().mapToInt(CreditRating::rating).sum()
        return total / ratings.size
    }
}