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

    println("Found ${pairs.size} pairs.")
    pairs.map { " ${it.first} + ${it.second} = $result, ${it.first} * ${it.second} = ${it.first * it.second}" }
            .forEach { println(it) }
}

