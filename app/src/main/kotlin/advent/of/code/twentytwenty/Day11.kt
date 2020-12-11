package advent.of.code.twentytwenty

fun main(args: Array<String>) {
    val seatPlan = newSeatPlan(getResourceAsText("/day11-input"))
    val stabilizedSeatPlanPart1 = stabilizeSeatPlan(seatPlan, ::countAdjacentOccupiedSeats, 4)
    val occupiedSeatsPart1 = countOccupiedSeats(stabilizedSeatPlanPart1)
    println("Part 1: found $occupiedSeatsPart1 occupied seats in seat plan")


    val stabilizedSeatPlanPart2 = stabilizeSeatPlan(seatPlan, ::countFirstOccupiedSeatInAllDirections, 5)
    val occupiedSeatsPart2 = countOccupiedSeats(stabilizedSeatPlanPart2)
    println("Part 2: found $occupiedSeatsPart2 occupied seats in seat plan")
}

abstract class Position(val icon: Char) {
    fun countOccupied() = when (this) {
        is Occupied -> 1
        else -> 0
    }
}

object Floor : Position('.')
abstract class Seat(icon: Char) : Position(icon)
object Occupied : Seat('#')
object Empty : Seat('L')
typealias SeatPlan = List<List<Position>>
typealias CountSeats = (x: Int, y: Int) -> Int

fun stabilizeSeatPlan(seatPlan: SeatPlan, countSeats: (seatPlan: SeatPlan) -> CountSeats, occupiedLimit: Int) =
        generateSequence(seatPlan, {
            val newSeatPlan = iterate(it, countSeats(it), occupiedLimit)
            if (newSeatPlan == it) null else newSeatPlan
        })
                .last()

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
                .sumBy { it.countOccupied() }

private fun iterate(seatPlan: SeatPlan, countSeats: CountSeats, occupiedLimit: Int): SeatPlan {
    val (maxX, maxY) = size(seatPlan)

    fun iteratePosition(x: Int, y: Int): Position =
            when (val currentPosition = seatPlan[x][y]) {
                is Empty -> if (countSeats(x, y) == 0) Occupied else Empty
                is Occupied -> if (countSeats(x, y) >= occupiedLimit) Empty else Occupied
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

fun countAdjacentOccupiedSeats(seatPlan: SeatPlan): CountSeats {
    return fun(x: Int, y: Int): Int = adjacentSeats(x, y, seatPlan)
            .map { (x, y) -> seatPlan[x][y] }
            .count { it is Occupied }
}

private fun adjacentSeats(x: Int, y: Int, seatPlan: SeatPlan): List<Pair<Int, Int>> {
    val seatPlanRange = inRange(seatPlan)

    return listOf(
            Pair(x - 1, y - 1), Pair(x, y - 1), Pair(x + 1, y - 1),
            Pair(x - 1, y), Pair(x + 1, y),
            Pair(x - 1, y + 1), Pair(x, y + 1), Pair(x + 1, y + 1)
    )
            .filter { (x, y) -> seatPlanRange(x, y) }
}

private fun inRange(seatPlan: SeatPlan): (x: Int, y: Int) -> Boolean {
    val (maxX, maxY) = size(seatPlan)
    return fun(x, y) = x in 0 until maxX && y in 0 until maxY
}

fun countFirstOccupiedSeatInAllDirections(seatPlan: SeatPlan): CountSeats =
        fun(x: Int, y: Int): Int = adjacentSeats(x, y, seatPlan)
                .map { (adjacentX, adjacentY) -> navigateToSeat(Pair(adjacentX, adjacentY), Pair(adjacentX - x, adjacentY - y), seatPlan) }
                .count { it is Occupied }

fun navigateToSeat(position: Pair<Int, Int>, direction: Pair<Int, Int>, seatPlan: SeatPlan): Seat? {
    if (!inRange(seatPlan)(position.first, position.second)) {
        return null
    }
    return when (val current = seatPlan[position.first][position.second]) {
        is Seat -> current
        else -> navigateToSeat(Pair(position.first + direction.first, position.second + direction.second), direction, seatPlan)
    }
}

private fun size(seatPlan: SeatPlan): Pair<Int, Int> = Pair(seatPlan.size, seatPlan[0].size)
