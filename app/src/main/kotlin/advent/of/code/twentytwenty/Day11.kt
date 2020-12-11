package advent.of.code.twentytwenty

fun main(args: Array<String>) {
    val seatPlan = newSeatPlan(getResourceAsText("/day11-input"))
    val stabilizedSeatPlan = stabilizeSeatPlan(seatPlan)
    val occupiedSeats = countOccupiedSeats(stabilizedSeatPlan)
    println("Part 1: found $occupiedSeats occupied seats in seat plan")
}

abstract class Position(val icon: Char)
object Floor : Position('.')
abstract class Seat(icon: Char) : Position(icon)
object Occupied : Seat('#')
object Empty : Seat('L')
typealias SeatPlan = List<List<Position>>

fun stabilizeSeatPlan(seatPlan: SeatPlan) = generateSequence(seatPlan, {
    val newSeatPlan = iterateSeatPlan(it)
    if (newSeatPlan == it) null else newSeatPlan
})
        .last()


fun countOccupiedSeats(position: Position) =
        when (position) {
            is Occupied -> 1
            else -> 0
        }

fun newSeatPlan(text: String): SeatPlan = text.lines()
        .map {
            it.map { char ->
                when (char) {
                    Occupied.icon -> Occupied
                    Empty.icon -> Empty
                    else -> Floor
                }
            }
        }


fun countOccupiedSeats(seatPlan: SeatPlan) =
        seatPlan.flatten()
                .sumBy { countOccupiedSeats(it) }

fun iterateSeatPlan(seatPlan: SeatPlan): SeatPlan {
    val maxX = seatPlan.size
    val maxY = seatPlan[0].size

    fun countOcuppied(x: Int, y: Int): Int =
            if (x !in 0 until maxX || y !in 0 until maxY) 0
            else countOccupiedSeats(seatPlan[x][y])

    fun countAdjacentOccupiedSeats(x: Int, y: Int): Int =
            listOf(
                    Pair(x - 1, y - 1), Pair(x, y - 1), Pair(x + 1, y - 1),
                    Pair(x - 1, y), Pair(x + 1, y),
                    Pair(x - 1, y + 1), Pair(x, y + 1), Pair(x + 1, y + 1)
            )
                    .map { (x, y) -> countOcuppied(x, y) }
                    .sum()

    fun iteratePosition(x: Int, y: Int): Position =
            when (val currentPosition = seatPlan[x][y]) {
                is Empty -> if (countAdjacentOccupiedSeats(x, y) == 0) Occupied else Empty
                is Occupied -> if (countAdjacentOccupiedSeats(x, y) >= 4) Empty else Occupied
                else -> currentPosition
            }

    return generateSequence(0, { it + 1 })
            .take(maxX)
            .map {
                generateSequence(Pair(it, 0), { (x, y) -> Pair(x, y + 1) })
                        .take(maxY)
                        .map { (x, y) -> iteratePosition(x, y) }
                        .toList()
            }
            .toList()
}
