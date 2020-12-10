package day2

import java.util.stream.Stream
import kotlin.streams.toList

fun main(args: Array<String>) {
    val validPasswords = filterValidPasswords(getResourceAsLines())

    println("Found ${validPasswords.size} valid passwords")
}

fun filterValidPasswords(lines: Stream<String>): List<Pair<Policy, Password>> {
    return lines.map { parsePolicyAndPassword(it) }
            .filter { (policy, password) -> validator(policy)(password) }
            .toList()
}

data class Policy(val lowerBound: Int, val upperBound: Int, val character: Char)
data class Password(val value: String)

fun parsePolicyAndPassword(line: String): Pair<Policy, Password> {
    val (policyTokens, passwordValue) = line.split(":")
    val (policyBoundaryTokens, character) = policyTokens.split(" ")
    val (lowerBound, upperBound) = policyBoundaryTokens.split("-")
    val policy = Policy(lowerBound.toInt(), upperBound.toInt(), character.single())
    val password = Password(passwordValue.trim())

    return Pair(policy, password)
}

fun validator(policy: Policy): (Password) -> Boolean {
    return fun(password: Password): Boolean {
        val occurrences = password.value.asIterable()
                .count { it == policy.character }
        return occurrences in policy.lowerBound..policy.upperBound
    }
}

fun getResourceAsLines(): Stream<String> = object {}.javaClass.getResourceAsStream("/input")
        .bufferedReader()
        .lines()