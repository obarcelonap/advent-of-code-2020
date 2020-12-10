package day1

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
