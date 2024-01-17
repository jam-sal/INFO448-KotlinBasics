package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    when(arg) {
        "Hello" -> return "world"
        is String -> return "Say what?"
        0 -> return "zero"
        1 -> return "one"
        in 2..10 -> return "low number"
        is Int -> return "a number"
        else -> return "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int {
    return lhs + rhs
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int {
    return lhs - rhs
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int): Int {
    return op(lhs, rhs)
}

// write a class "Person" with first name, last name and age
class Person(var firstName: String, val lastName: String, var age: Int) {
    val debugString: String get() = "[Person firstName:${firstName} lastName:${lastName} age:${age}]"
}

// write a class "Money"
class Money(val amount: Int, val currency: String) {
    init {
        val validCurrencies = listOf("USD", "GBP", "CAN", "EUR")
        if(amount < 0) {
            throw IllegalArgumentException("Amount cannot be negative")
        }
        if(currency !in validCurrencies) {
            throw IllegalArgumentException("Currency type must be: USD, GBP, CAN, or EUR")
        }
    }

    fun convert(newCurrency: String): Money {
        val validCurrencies = listOf("USD", "GBP", "CAN", "EUR")
        if(newCurrency !in validCurrencies) {
            throw IllegalArgumentException("Currency type must be: USD, GBP, CAN, or EUR")
        }
        return if(newCurrency == this.currency) {
            Money(this.amount, this.currency)
        } else {
            if(this.currency == "USD" && newCurrency == "GBP") {
                Money((this.amount * .5).toInt(), "GBP")
            } else if(this.currency == "USD" && newCurrency == "EUR") {
                Money((this.amount * 1.5).toInt(), "EUR")
            } else if(this.currency == "USD" && newCurrency == "CAN") {
                Money((this.amount * 1.25).toInt(), "CAN")
            } else if(this.currency == "GBP" && newCurrency == "USD") {
                Money((this.amount * 2).toInt(), "USD")
            } else if(this.currency == "CAN" && newCurrency == "USD") {
                Money((this.amount * 0.8).toInt(), "USD")
            } else if(this.currency == "EUR" && newCurrency == "USD") {
                Money((this.amount * 2/3).toInt(), "USD")
            } else {
                convert("USD").convert(newCurrency)
            }
        }
    }

    operator fun plus(other: Money): Money {
        return Money(this.amount + (other.convert(this.currency)).amount, this.currency)
    }
}