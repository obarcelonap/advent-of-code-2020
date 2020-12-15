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

    @Test
    fun shouldSatisfyPuzzleExampleOfPart2() {
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
        val slopes = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
        val trees = countTrees(grid, slopes)

        assertEquals(336, trees)
    }
}