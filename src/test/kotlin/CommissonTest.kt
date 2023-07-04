import org.junit.Test
import kotlin.test.assertEquals

class CommissionTest {

    @Test
    fun testCommissionForMastercardWithinLimit() {
        val result = comission(TYPE_MASTERCARD, 50_000, 100_000)
        assertEquals(250, result)
    }

    @Test
    fun testCommissionForMastercardExceedsLimit() {
        val result = comission(TYPE_MASTERCARD, 100_000, 100_000)
        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun testCommissionForVisaWithinLimit() {
        val result = comission(TYPE_VISA, 5_000, 100_000)
        assertEquals(35, result)
    }

    @Test
    fun testCommissionForVisaExceedsLimit() {
        val result = comission(TYPE_VISA, 700_000, 100_000)
        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun testCommissionForUnknownCardType() {
        val result = comission("Amex", 10_000, 100_000)
        assertEquals(ERROR_TYPE, result)
    }
    @Test
    fun testCommissionForVisaWithinLimitAndPreviousLimit() {
        val result = comission(TYPE_VISA, 5_000, 1_000)
        assertEquals(0, result)
    }

    @Test
    fun testCommissionForVisaExceedsLimitAndPreviousLimit() {
        val result = comission(TYPE_VISA, 700_000, 800_000)
        assertEquals(ERROR_LIMIT, result)
    }

}
