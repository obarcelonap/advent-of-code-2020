package advent.of.code.twentytwenty

import org.junit.Test
import kotlin.test.assertEquals

class Day15Test {

    @Test
    fun shouldSatisfyExamplesOfPart1() {
        listOf(
                Pair(listOf(0, 3, 6), 436),
                Pair(listOf(1, 3, 2), 1),
                Pair(listOf(2, 1, 3), 10),
                Pair(listOf(1, 2, 3), 27),
                Pair(listOf(2, 3, 1), 78),
                Pair(listOf(3, 2, 1), 438),
                Pair(listOf(3, 1, 2), 1836)
        )
                .forEach { (startingNumbers, expectedNumber) ->
                    val actualNumber = numberSpokenAt(2020, startingNumbers)
                    assertEquals(expectedNumber, actualNumber)
                }
    }
}