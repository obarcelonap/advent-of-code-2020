package advent.of.code.twentytwenty

fun main() {
    val startingNumbers = listOf(1, 12, 0, 20, 8, 16)
    val numberSpokenAt2020 = numberSpokenAt(2020, startingNumbers)
    println("Part1: number spoken at 2020 is $numberSpokenAt2020")

    val numberSpokenAt30000000 = numberSpokenAt(30000000, startingNumbers)
    println("Part2: number spoken at 30000000 is $numberSpokenAt30000000")
}

fun numberSpokenAt(targetTurn: Int, startingNumbers: List<Int>): Int {
    if (targetTurn < startingNumbers.size) {
        return startingNumbers[targetTurn - 1]
    }

    tailrec fun numberSpoken(numbers: MutableMap<Int, Int>, lastNumber: Int, turn: Int = 1): Int {
        if (targetTurn == turn) {
            return lastNumber
        }

        val nextNumber =
                if (numbers.containsKey(lastNumber)) turn - numbers.getValue(lastNumber)
                else 0

        numbers[lastNumber] = turn
        return numberSpoken(numbers, nextNumber, turn + 1)
    }

    val numbers = startingNumbers.dropLast(1)
            .mapIndexed { turn, number -> Pair(number, turn + 1) }
            .toMap()
            .toMutableMap()

    val nextNumber = startingNumbers.last()
    return numberSpoken(numbers, nextNumber, numbers.size + 1)
}