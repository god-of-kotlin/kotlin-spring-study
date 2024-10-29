package com.group.libraryapp.calculator

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
    calculatorTest.divideTest()
}

class CalculatorTest {

    fun addTest() {
        //given
        val calculator = Calculator(5)
        //when
        calculator.add(3)
        //then
        if (calculator.number != 8) {
            throw IllegalArgumentException()
        }
    }


    fun minusTest() {
        //given
        val calculator = Calculator(5)
        //when
        calculator.minus(3)
        //then
        if (calculator.number != 2) {
            throw IllegalArgumentException()
        }
    }

    fun multiplyTest() {
        //given
        val calculator = Calculator(5)
        //when
        calculator.multiply(3)
        //then
        if (calculator.number != 15) {
            throw IllegalArgumentException()
        }
    }

    fun divideTest() {
        //given
        val calculator = Calculator(15)
        //when
        calculator.divide(3)
        //then
        if (calculator.number != 5) {
            throw IllegalArgumentException()
        }
    }

}