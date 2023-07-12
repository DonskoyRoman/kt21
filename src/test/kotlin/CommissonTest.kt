import org.junit.Test
import kotlin.test.assertEquals
import kotlin.math.max

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


    @Test
    fun testCommissionForMastercardExceedLimit() {
        val result = commission(TYPE_MASTERCARD, 160_000, 0)
        assertEquals(ERROR_LIMIT, result)
    }




    @Test
    fun testCommissionForVisaExceedLimit() {
        val result = commission(TYPE_VISA, 700_000, 0)
        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun testCommissionForInvalidCardType() {
        val result = commission("American Express", 10_000, 0)
        assertEquals(ERROR_TYPE, result)
    }

    private fun commission(type: String, amount: Int, previous: Int): Int =
        when (type) {
            TYPE_MASTERCARD -> if (amount + previous > 150_000) ERROR_LIMIT else max(35, (amount * 0.005).toInt())
            TYPE_VISA -> {
                when {
                    amount + previous <= 10_000 -> 0
                    amount + previous > 600_000 -> ERROR_LIMIT
                    else -> max(35, (amount * 0.005).toInt())
                }
            }
            else -> ERROR_TYPE
        }
}


