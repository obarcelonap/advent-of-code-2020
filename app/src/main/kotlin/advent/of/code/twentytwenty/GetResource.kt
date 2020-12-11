package advent.of.code.twentytwenty

import kotlin.streams.toList

fun getResourceAsLines(resourcePath: String): List<String> = object {}.javaClass.getResourceAsStream(resourcePath)
        .bufferedReader()
        .lines()
        .toList()

fun getResourceAsText(resourcePath: String): String = object {}.javaClass.getResource(resourcePath)
        .readText()
