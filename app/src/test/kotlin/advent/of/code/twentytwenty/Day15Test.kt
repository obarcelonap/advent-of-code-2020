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

    @Test
    fun shouldSatisfyExamplesOfPart2() {
        listOf(
                Pair(listOf(0, 3, 6), 175594),
                Pair(listOf(1, 3, 2), 2578),
                Pair(listOf(2, 1, 3), 3544142),
                Pair(listOf(1, 2, 3), 261214),
                Pair(listOf(2, 3, 1), 6895259),
                Pair(listOf(3, 2, 1), 18),
                Pair(listOf(3, 1, 2), 362)
        )
                .forEach { (startingNumbers, expectedNumber) ->
                    val actualNumber = numberSpokenAt(30000000, startingNumbers)
                    assertEquals(expectedNumber, actualNumber)
                }
    }
}