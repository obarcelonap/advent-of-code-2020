package advent.of.code.twentytwenty

fun main() {
    val numberSpoken = numberSpokenAt(2020, listOf(1, 12, 0, 20, 8, 16))
    println("Part1: number spoken at 2020 is $numberSpoken")

}

fun numberSpokenAt(targetTurn: Int, startingNumbers: List<Int>): Int {
    if (targetTurn < startingNumbers.size) {
        return startingNumbers[targetTurn - 1]
    }

    fun numberSpoken(numbers: Map<Int, Int>, lastNumber: Int, turn: Int = 1): Int {
        if (targetTurn == turn) {
            return lastNumber
        }

        val nextNumber =
                if (numbers.containsKey(lastNumber)) turn - numbers.getValue(lastNumber)
                else 0

        return numberSpoken(numbers + Pair(lastNumber, turn), nextNumber, turn + 1)
    }

    val numbers = startingNumbers.dropLast(1)
            .mapIndexed { turn, number -> Pair(number, turn + 1) }.toMap()
    val nextNumber = startingNumbers.last()
    return numberSpoken(numbers, nextNumber, numbers.size + 1)
}