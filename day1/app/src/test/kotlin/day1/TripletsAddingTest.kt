package day1

import org.junit.Test
import kotlin.test.assertEquals

class TripletsAddingTest {

    @Test
    fun shouldReturnEmpty_whenNumbersIsSmallerThan3() {
        val list = tripletsAdding(2020, emptyList())
        assertEquals(emptyList(), list)
    }

    @Test
    fun shouldReturnEmpty_whenNoCombinationOfNumberAddsTheResult() {
        val list = tripletsAdding(2020, listOf(1, 2, 3))
        assertEquals(emptyList(), list)
    }

    @Test
    fun shouldReturnATriplet_whenNumbersContainsTheResultAndZero() {
        val list = tripletsAdding(2020, listOf(0, 0, 2020))
        assertEquals(listOf(Triple(0, 0, 2020)), list)
    }

    @Test
    fun shouldReturnATriplet_whenAddingNumbersMatchTheExpectedResult() {
        val list = tripletsAdding(2020, listOf(1, 1010, 10, 10, 0, 1000, 10))
        assertEquals(listOf(Triple(1010, 10, 1000)), list)
    }
}