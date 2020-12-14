package advent.of.code.twentytwenty

import org.junit.Test
import kotlin.test.assertEquals

class Day14Test {

    @Test
    fun shouldModifyValue_whenBitsOfMaskAreDifferent() {
        val value = 11L
        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"

        val newValue = applyMask(value, mask)

        assertEquals(73, newValue)
    }

    @Test
    fun shouldKeepValue_whenSetBitsMatches() {
        val value = 101L
        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"

        val newValue = applyMask(value, mask)

        assertEquals(101, newValue)
    }

    @Test
    fun shouldReturnTheMask_whenValueIsZero() {
        val value = 0L
        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"

        val newValue = applyMask(value, mask)

        assertEquals(64, newValue)
    }

    @Test
    fun shouldValidateFirstExample() {
        val input = """
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent()

        val sum = memorySum(input, ::valueBitMaskDecoder)

        assertEquals(165, sum)
    }

    @Test
    fun shouldValidateSecondExample() {
        val input = """
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
        """.trimIndent()

        val sum = memorySum(input, ::memoryAddressDecoder)

        assertEquals(208, sum)
    }
}