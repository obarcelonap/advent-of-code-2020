package advent.of.code.twentytwenty

import java.lang.Long.parseLong
import java.lang.Long.toBinaryString

typealias Mask = String
typealias Memory = Map<Long, Long>
typealias MaskedMemory = Pair<Memory, Mask>

fun main() {
    val input = getResourceAsText("/day14-input")
    val part1Sum = memorySum(input, ::valueBitMaskDecoder)
    println("Part1: final memory sum is $part1Sum")

    val part2Sum = memorySum(input, ::memoryAddressDecoder)
    println("Part2: final memory sum is $part2Sum")

}

fun memorySum(input: String, decoder: (mem: MaskedMemory, address: Long, value: Long) -> MaskedMemory): Long {
    val initialMask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
    val initialMemory = emptyMap<Long, Long>()

    val (finalMemory) = input.lines()
            .map(fun(line: String): (mem: MaskedMemory) -> MaskedMemory {
                if (line.startsWith("mask")) {
                    return { (mem) -> Pair(mem, parseMask(line)) }
                }
                return { mem ->
                    val (address, value) = parseMemoryWrite(line)
                    decoder(mem, address, value)
                }
            })
            .fold(Pair(initialMemory, initialMask), { mem, instruction -> instruction(mem) })

    return finalMemory.map { (_, value) -> value }
            .sum()
}

fun valueBitMaskDecoder(maskedMemory: MaskedMemory, address: Long, value: Long): MaskedMemory {
    val (mem, mask) = maskedMemory
    return Pair(mem + Pair(address, applyMask(value, mask)), mask)
}

fun memoryAddressDecoder(maskedMemory: MaskedMemory, address: Long, value: Long): MaskedMemory {
    val (mem, mask) = maskedMemory

    val maskedAddr = toBinaryString(address or onesMask(mask))
            .padStart(mask.length, '0')
            .mapIndexed { index, character -> if (mask[index] == 'X') 'X' else character }
            .joinToString("")

    fun generateAddresses(value: String): List<String> {
        if (!value.contains('X')) {
            return listOf(value)
        }
        return generateAddresses(value.replaceFirst('X', '0')) + generateAddresses(value.replaceFirst('X', '1'))
    }

    val newMemory = mem + generateAddresses(maskedAddr)
            .map { Pair(parseLong(it, 2), value) }

    return Pair(newMemory, mask)
}

fun applyMask(value: Long, mask: String): Long = value or onesMask(mask) and zerosMask(mask)

private fun zerosMask(mask: String) = mask.map { character ->
    when (character) {
        '0' -> '1'
        else -> '0'
    }
}
        .joinToBinaryLong()
        .inv()

private fun onesMask(mask: String): Long = mask.map { character ->
    when (character) {
        '1' -> '1'
        else -> '0'
    }
}
        .joinToBinaryLong()


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
