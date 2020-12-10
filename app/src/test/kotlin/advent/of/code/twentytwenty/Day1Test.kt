package advent.of.code.twentytwenty

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test fun shouldSatisfyPuzzleExampleOfPart1() {
        val pairs = pairsAdding(
                2020,
                listOf(
                        1721,
                        979,
                        366,
                        299,
                        675,
                        1456
                ))

        assertEquals(listOf(Pair(1721, 299)), pairs)
    }

    @Test fun shouldSatisfyPuzzleExampleOfPart2() {
        val triplets = tripletsAdding(
                2020,
                listOf(
                        1721,
                        979,
                        366,
                        299,
                        675,
                        1456
                ))

        assertEquals(listOf(Triple(979, 366, 675)), triplets)
    }
}
