package advent.of.code.twentytwenty

import java.lang.Long.parseLong

typealias Mask = String
typealias Memory = Map<Long, Long>
typealias MaskedMemory = Pair<Memory, Mask>

fun main() {
    val sum = memorySum(getResourceAsText("/day14-input"))
    println("Part1: final memory sum is $sum")
}

fun memorySum(input: String): Long {
    val initialMask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
    val initialMemory = emptyMap<Long, Long>()

    val (finalMemory) = input.lines()
            .map(fun(line: String): (mem: MaskedMemory) -> MaskedMemory {
                if (line.startsWith("mask")) {
                    return { (mem) -> Pair(mem, parseMask(line)) }
                }
                return { (mem, mask) ->
                    val (address, value) = parseMemoryWrite(line)
                    Pair(mem + Pair(address, applyMask(value, mask)), mask)
                }
            })
            .fold(Pair(initialMemory, initialMask), { mem, instruction -> instruction(mem) })

    return finalMemory.map { (_, value) -> value }
            .sum()
}


fun applyMask(value: Long, mask: String): Long {
    val onesMask = mask.map { character ->
        when (character) {
            '1' -> '1'
            else -> '0'
        }
    }
            .joinToBinaryLong()

    val zerosMask = mask.map { character ->
        when (character) {
            '0' -> '1'
            else -> '0'
        }
    }
            .joinToBinaryLong()
            .inv()

    return value or onesMask and zerosMask
}


private fun <T> Iterable<T>.joinToBinaryLong(): Long {
    return parseLong(joinToString(""), 2)
}

private fun parseMask(line: String): String = line.split("=")[1].trim()

private fun parseMemoryWrite(line: String): Pair<Long, Long> {
    val (addressTokens, value) = line.split("=")
    val re = Regex("[^0-9]")
    val address = re.replace(addressTokens.trim(), "").toLong()

    return Pair(address, value.trim().toLong())
}
