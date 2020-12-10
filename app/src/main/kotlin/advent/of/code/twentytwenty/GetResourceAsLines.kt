package advent.of.code.twentytwenty

import kotlin.streams.toList

fun getResourceAsLines(resourcePath: String): List<String> = object {}.javaClass.getResourceAsStream(resourcePath)
        .bufferedReader()
        .lines()
        .toList()