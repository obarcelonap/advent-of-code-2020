package advent.of.code.twentytwenty

fun main() {
    val input = getResourceAsText("/day3-input")

    val slope = Pair(3, 1)
    val part1Count = countTrees(input, slope)
    println("Part1: count of trees is $part1Count")

    val slopes = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
    val part2Count = countTrees(input, slopes)
    println("Part2: count of trees is $part2Count")
}

sealed class Square {
    object Open : Square()
    object Tree : Square()

    override fun toString(): String {
        return when (this) {
            Tree -> "#"
            else -> "."
        }
    }
}

fun countTrees(text: String, slope: Pair<Int, Int>): Int = countTrees(parseGrid(text), slope)

fun countTrees(text: String, slopes: List<Pair<Int, Int>>): Int {
    val grid = parseGrid(text)
    return slopes.map { countTrees(grid, it) }
            .multiply()
}

private fun Iterable<Int>.multiply(): Int {
    return reduce { acc, e -> acc * e }
}

private fun countTrees(grid: List<List<Square>>, slope: Pair<Int, Int>): Int {
    fun navigateTilEnd(currentGrid: List<List<Square>>, position: Pair<Int, Int> = Pair(0, 0)): List<Square> {
        val (x, y) = position
        if (x >= currentGrid.size) {
            return emptyList()
        }
        if (y >= currentGrid[0].size) {
            val offset = x + (grid.size - currentGrid.size)
            return navigateTilEnd(grid.subList(offset, grid.size), Pair(0, y - currentGrid[0].size))
        }

        val square = currentGrid[x][y]
        return navigateTilEnd(currentGrid, Pair(x + slope.second, y + slope.first)) + square

    }

    return navigateTilEnd(grid)
            .count { it is Square.Tree }
}

private fun toString(grid: List<List<Square>>) = grid.joinToString("") { it.joinToString("", postfix = "\n") }

private fun parseGrid(text: String) = text.lines()
        .map { line ->
            line.map {
                when (it) {
                    '#' -> Square.Tree
                    else -> Square.Open
                }
            }.toList()
        }
        .toList()