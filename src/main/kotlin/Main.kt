import kotlin.math.max

const val TYPE_MASTERCARD = "MasterCard"
const val TYPE_VISA = "Visa"

const val ERROR_LIMIT = -1
const val ERROR_TYPE = -2

fun main() {
    val result = comission(TYPE_VISA, 10_000, 200_000)
    when (result) {
        ERROR_TYPE -> println("Wrong card type")
        ERROR_LIMIT -> println("Limit exceed")
        else -> println(result)
    }
}

fun comission(type: String, amount: Int, previous: Int): Int =
    when (type) {
        TYPE_MASTERCARD -> if (amount + previous > 150_000) -1 else max(35, (amount * 0.005).toInt())
        TYPE_VISA -> {
            when {
                amount + previous <= 10_000 -> 0
                amount + previous > 600_000 -> ERROR_LIMIT
                else -> max(35, (amount * 0.005).toInt())


            }
        }

        else -> ERROR_TYPE
    }