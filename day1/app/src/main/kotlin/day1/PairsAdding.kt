package day1

fun <T> List<T>.tail() = drop(1)

fun pairsAdding(result: Int, numbers: List<Int>): List<Pair<Int, Int>> {
    if (numbers.isEmpty()) {
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