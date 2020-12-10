package day2

import java.util.stream.Stream
import kotlin.streams.toList

fun main(args: Array<String>) {
    val lines = getResourceAsLines().toList()

    val validPasswordsByOcurrencesRange = filterValidPasswords(lines, ::validatorByOccurrencesRange)
    println("Part 1: found ${validPasswordsByOcurrencesRange.size} valid passwords")

    val validPasswordsByUniqueIndexAssertion = filterValidPasswords(lines, ::validatorByUniqueIndexAssertion)
    println("Part 2: found ${validPasswordsByUniqueIndexAssertion.size} valid passwords")
}

fun filterValidPasswords(lines: Iterable<String>, policyValidator: (Policy) -> (Password) -> Boolean): List<Pair<Policy, Password>> {
    return lines.map { parsePolicyAndPassword(it) }
            .filter { (policy, password) -> policyValidator(policy)(password) }
            .toList()
}

data class Policy(val firstInt: Int, val secondInt: Int, val character: Char)
data class Password(val value: String)

fun parsePolicyAndPassword(line: String): Pair<Policy, Password> {
    val (policyTokens, passwordValue) = line.split(":")
    val (policyBoundaryTokens, character) = policyTokens.split(" ")
    val (lowerBound, upperBound) = policyBoundaryTokens.split("-")
    val policy = Policy(lowerBound.toInt(), upperBound.toInt(), character.single())
    val password = Password(passwordValue.trim())

    return Pair(policy, password)
}

fun validatorByOccurrencesRange(policy: Policy): (Password) -> Boolean {
    return fun(password: Password): Boolean {
        val (lowerBound, upperBound, character) = policy
        val occurrences = password.value.asIterable()
                .count { it == character }
        return occurrences in lowerBound..upperBound
    }
}

fun validatorByUniqueIndexAssertion(policy: Policy): (Password) -> Boolean {
    return fun(password: Password): Boolean {
        val (firstIndex, secondIndex, character) = policy
        val firstIndexChar = password.value[firstIndex - 1]
        val secondIndexChar = password.value[secondIndex - 1]
        return (firstIndexChar == character || secondIndexChar == character)
                && firstIndexChar != secondIndexChar
    }
}

fun getResourceAsLines(): Stream<String> = object {}.javaClass.getResourceAsStream("/input")
        .bufferedReader()
        .lines()