package credit.history.first

import hu.pinter.beci.credit.history.first.CreditHistory
import hu.pinter.beci.credit.history.first.CreditRating
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ACreditHistory {
    private lateinit var testCreditHistory: CreditHistory

    constructor() {
        println("constructor initialization ready!")
    }

    @BeforeEach
    fun setUp() {
        testCreditHistory = CreditHistory()
        println("'testCreditHistory' initialization ready!")
    }

    //JUnit 5 --> minden test metódus package-level acces-szel van megáldva

    @Test
    fun withNoCreditRatingsHas0Mean() {
        val result = testCreditHistory.arithmeticMean()

        assertEquals(0, result)
    }

    //-----------------------------------------------------------
    fun withOneRatingHasEquivalentMean() {
        //arrange(2):

        //set-up step usually
        //involves creating objects and calling methods or setting data on them
        //Some tests won’t have any arrange needs (for
        //example, when you’re making a static method call with literal or no
        //arguments).
        testCreditHistory.add(CreditRating(25))

        //act:(1)
        //create the behavior you’re trying to test
        val actual = testCreditHistory.arithmeticMean()

        //assert: (3)
        //(verify) that the system behaves the way you expect
        assertEquals(25, actual)

        //ha az 'add' visszatéríti az objektumot, akkor egy sorban tudok tesztet írni
        //assertEquals(25, CreditHistory().add(CreditRating(25)).arithmeticMean())
        //az 'act' fázisban lévő metódus esetén kommenteljek ki részeket, ha
        // esetleg egy másik metódusba történik áthívás, máshonnan jön érték
        //lást, ha 'add'-ban kikommentezem a részt, vége a dalnak
        //(1,2,3) olvasási sorrend
    }

    //ZOM -  Zero-one-many miatt levettem a '@Test' annotációt a két átfedésben lévő tesztekről,
    //a one-based (withOneRatingHasEquivalentMean) test másolásra kerül
    // ha nézzük a 'withMultipleRatingsDividesTotalByCount', ezt elimináljuk egyesítjük azokat
    fun withMultipleRatingsDividesTotalByCount() {
        //arrange
        testCreditHistory.add(CreditRating(780))
        testCreditHistory.add(CreditRating(800))
        testCreditHistory.add(CreditRating(820))

        //act
        val actual = testCreditHistory.arithmeticMean()

        //asserts
        assertEquals(800, actual)
    }
    //-----------------------------------------------------------

    @Test
    fun withRatingsDividesTotalByCount() {
        //arrange
        testCreditHistory.add(CreditRating(780))
        testCreditHistory.add(CreditRating(800))
        testCreditHistory.add(CreditRating(820))

        //act
        val actual = testCreditHistory.arithmeticMean()

        //assert
        assertEquals(800, actual)
    }

}