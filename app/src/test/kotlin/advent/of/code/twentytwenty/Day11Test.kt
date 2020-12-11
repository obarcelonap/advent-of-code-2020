package advent.of.code.twentytwenty

import org.junit.Test
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun shouldSatisfyPuzzleExampleOfPart1() {
        val seatPlan = newSeatPlan("""
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """.trimIndent())

        val stabilizedSeatPlan = stabilizeSeatPlan(seatPlan)

        assertEquals(
                newSeatPlan("""
                    #.#L.L#.##
                    #LLL#LL.L#
                    L.#.L..#..
                    #L##.##.L#
                    #.#L.LL.LL
                    #.#L#L#.##
                    ..L.L.....
                    #L#L##L#L#
                    #.LLLLLL.L
                    #.#L#L#.##
                """.trimIndent()),
                stabilizedSeatPlan
        )

        val occupiedSeats = countOccupiedSeats(stabilizedSeatPlan)
        assertEquals(37, occupiedSeats)
    }

}