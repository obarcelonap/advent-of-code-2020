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

        val stabilizedSeatPlan = stabilizeSeatPlan(seatPlan, ::countAdjacentOccupiedSeats, 4)

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

    @Test
    fun shouldCountEight_whenFirstSeatInAllDirectionsIsOccupied() {
        val seatPlan = newSeatPlan("""
            .......#.
            ...#.....
            .#.......
            .........
            ..#L....#
            ....#....
            .........
            #........
            ...#.....
        """.trimIndent())

        val count = countFirstOccupiedSeatInAllDirections(seatPlan)(4, 3)
        assertEquals(8, count)
    }

    @Test
    fun shouldCountZero_whenEmptySeatsAreNotTheFirstOnes() {
        val seatPlan = newSeatPlan("""
            .............
            .L.L.#.#.#.#.
            .............
        """.trimIndent())

        val count = countFirstOccupiedSeatInAllDirections(seatPlan)(1, 1)
        assertEquals(0, count)
    }

    @Test
    fun shouldCountZero_whenNoSeatsAreInEightDirections() {
        val seatPlan = newSeatPlan("""
            .##.##.
            #.#.#.#
            ##...##
            ...L...
            ##...##
            #.#.#.#
            .##.##.
        """.trimIndent())

        val count = countFirstOccupiedSeatInAllDirections(seatPlan)(3, 3)
        assertEquals(0, count)
    }

    @Test
    fun shouldSatisfyPuzzleExampleOfPart2() {
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

        val stabilizedSeatPlan = stabilizeSeatPlan(seatPlan, ::countFirstOccupiedSeatInAllDirections, 5)

        assertEquals(
                newSeatPlan("""
                    #.L#.L#.L#
                    #LLLLLL.LL
                    L.L.L..#..
                    ##L#.#L.L#
                    L.L#.LL.L#
                    #.LLLL#.LL
                    ..#.L.....
                    LLL###LLL#
                    #.LLLLL#.L
                    #.L#LL#.L#
                """.trimIndent()),
                stabilizedSeatPlan
        )

        val occupiedSeats = countOccupiedSeats(stabilizedSeatPlan)
        assertEquals(26, occupiedSeats)
    }

}