package advent.of.code.twentytwenty

fun main(args: Array<String>) {
    val result = 2020
    val numbers: List<Int> = getResourceAsLines("/day1-input")
            .map { it.trim().toIntOrNull() }
            .toList()
            .filterNotNull()

    val pairs = pairsAdding(result, numbers)
    println("Part 1: found ${pairs.size} pairs.")
    pairs.map { " ${it.first} + ${it.second} = $result => ${it.first} * ${it.second} = ${it.first * it.second}" }
            .forEach { println(it) }

    val triplets = tripletsAdding(result, numbers)
    println("Part 2: found ${triplets.size} triplets.")
    triplets.map { " ${it.first} + ${it.second} + ${it.third} = $result => ${it.first} * ${it.second} * ${it.third} = ${it.first * it.second * it.third}" }
            .forEach { println(it) }
}

fun pairsAdding(result: Int, numbers: List<Int>): List<Pair<Int, Int>> {
    if (numbers.size < 2) {
        return emptyList()
    }

    fun pairsAdding(number: Int, candidates: List<Int>): List<Pair<Int, Int>> {
        if (candidates.isEmpty()) {
            return emptyList()
        }

        val matches = candidates.filter { it + number == result }
        if (matches.isEmpty()) {
            return pairsAdding(candidates.first(), candidates.tail())
        }

        val pairs = matches.map { Pair(number, it) }
        val newCandidates = candidates.filter { !matches.contains(it) }
        if (newCandidates.isEmpty()) {
            return pairs
        }

        return pairs + pairsAdding(newCandidates.first(), newCandidates)
    }

    return pairsAdding(numbers.first(), numbers.tail())
}

fun tripletsAdding(result: Int, numbers: List<Int>): List<Triple<Int, Int,Int>> {
    if (numbers.size < 3) {
        return emptyList()
    }

    fun tripletsAdding(number: Int, candidates: List<Int>): List<Triple<Int, Int,Int>> {
        if (candidates.size < 2) {
            return emptyList()
        }
        val reminder = result - number
        val pairs = pairsAdding(reminder, candidates)
        if (pairs.isEmpty()) {
            return tripletsAdding(candidates.first(), candidates.tail())
        }

        val triplets = pairs.map { Triple(number, it.first, it.second) }

        return triplets + tripletsAdding(candidates.first(), candidates.tail())
    }

    return tripletsAdding(numbers.first(), numbers.tail())
}
