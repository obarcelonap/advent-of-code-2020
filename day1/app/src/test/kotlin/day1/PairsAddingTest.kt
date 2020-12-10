package day1

import kotlin.test.Test
import kotlin.test.assertEquals

class PairsAddingTest {
    @Test
    fun shouldReturnEmpty_whenIsEmpty() {
        val list = pairsAdding(2020, emptyList())
        assertEquals(emptyList(), list)
    }

    @Test
    fun shouldReturnEmpty_whenNoCombinationOfNumberAddsTheResult() {
        val list = pairsAdding(2020, listOf(1, 2, 3))
        assertEquals(emptyList(), list)
    }

    @Test
    fun shouldReturnAPair_whenNumbersContainsTheResultAndZero() {
        val list = pairsAdding(2020, listOf(0, 1, 2, 2020))
        assertEquals(listOf(Pair(0, 2020)), list)
    }

    @Test
    fun shouldReturnAPair_whenAddingNumbersMatchTheExpectedResult() {
        val list = pairsAdding(2020, listOf(2019, 1))
        assertEquals(listOf(Pair(2019, 1)), list)
    }
}
