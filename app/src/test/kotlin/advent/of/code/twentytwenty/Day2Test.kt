package advent.of.code.twentytwenty

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {
    @Test
    fun shouldSatisfyPuzzleExampleOfPart1() {
        val validPasswords = filterValidPasswords(
                listOf(
                        "1-3 a: abcde",
                        "1-3 b: cdefg",
                        "2-9 c: ccccccccc"
                ),
                ::validatorByOccurrencesRange)

        assertEquals(
                listOf(
                        Pair(Policy(1, 3, 'a'), Password("abcde")),
                        Pair(Policy(2, 9, 'c'), Password("ccccccccc"))
                ),
                validPasswords)
    }

    @Test
    fun shouldSatisfyPuzzleExampleOfPart2() {
        val validPasswords = filterValidPasswords(
                listOf(
                        "1-3 a: abcde",
                        "1-3 b: cdefg",
                        "2-9 c: ccccccccc"
                ),
                ::validatorByUniqueIndexAssertion)

        assertEquals(
                listOf(
                        Pair(Policy(1, 3, 'a'), Password("abcde"))
                ),
                validPasswords)
    }
}
