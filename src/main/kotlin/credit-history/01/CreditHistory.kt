package hu.pinter.beci.`credit-history`.`01`

class CreditHistory {
    //nézz utána --> itt List<CreditRating> nem működik
    private val ratings: ArrayList<CreditRating> = ArrayList()
    fun add(credit: CreditRating) {
        ratings.add(credit)
    }

    fun arithmeticMean(): Int {
        if (ratings.isEmpty()) return 0

        val total = ratings.stream().mapToInt(CreditRating::rating).sum()
        return total / ratings.size
    }
}