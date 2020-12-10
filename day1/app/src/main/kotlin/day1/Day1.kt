package day1

import kotlin.streams.toList

fun main(args: Array<String>) {
    val result = 2020
    val numbers: List<Int> = object {}.javaClass.getResourceAsStream("/input")
            .bufferedReader()
            .lines()
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
