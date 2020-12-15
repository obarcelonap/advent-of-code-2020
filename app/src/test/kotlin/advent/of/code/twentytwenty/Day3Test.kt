package advent.of.code.twentytwenty

import org.junit.Test
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun shouldSatisfyPuzzleExampleOfPart1() {
        val grid = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent()
        val slope = Pair(3, 1)
        val trees = countTrees(grid, slope)

        assertEquals(7, trees)
    }
}