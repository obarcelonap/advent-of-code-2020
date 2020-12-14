package advent.of.code.twentytwenty

import org.junit.Test
import kotlin.test.assertEquals

class Day14Test {

    @Test
    fun shouldModifyValue_whenBitsOfMaskAreDifferent() {
        val value = 11
        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"

        val newValue = applyMask(value, mask)

        assertEquals(73, newValue)
    }

    @Test
    fun shouldKeepValue_whenSetBitsMatches() {
        val value = 101
        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"

        val newValue = applyMask(value, mask)

        assertEquals(101, newValue)
    }

    @Test
    fun shouldReturnTheMask_whenValueIsZero() {
        val value = 0
        val mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"

        val newValue = applyMask(value, mask)

        assertEquals(64, newValue)
    }
}