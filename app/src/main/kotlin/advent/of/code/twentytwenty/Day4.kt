package advent.of.code.twentytwenty

fun main() {
    val input = getResourceAsText("/day4-input")

    val part1Count = countValidPassports(input, ::requiredFieldsValidator)
    println("Part1: count of valid passwords is $part1Count")

    val part2Count = countValidPassports(input, ::fieldsValidator)
    println("Part2: count of valid passwords is $part2Count")
}

fun countValidPassports(input: String, validator: (passport: Map<String, String>) -> Boolean): Int {
    val passports = parsePassports(input)
    return passports.filter { validator(it) }
            .count()
}


fun fieldsValidator(passport: Map<String, String>): Boolean {
    return requiredFieldsValidator(passport)
            && passport.all { (name, value) ->
        when (name) {
            "byr" -> digits(value, 4) && between(value, 1920 to 2002)
            "iyr" -> digits(value, 4) && between(value, 2010 to 2020)
            "eyr" -> digits(value, 4) && between(value, 2020 to 2030)
            "hgt" -> (value.endsWith("cm") && between(value, 150 to 193))
                    || (value.endsWith("in") && between(value, 59 to 76))
            "hcl" -> Regex("#[a-f0-9]{6}").containsMatchIn(value)
            "ecl" -> listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)
            "pid" -> digits(value, 9)
            "cid" -> true
            else -> true
        }
    }
}

private fun between(value: String, bounds: Pair<Int, Int>): Boolean =
        between(value.stripNonNumeric().toIntOrNull() ?: 0, bounds)

private fun String.stripNonNumeric(): String = Regex("[^0-9]").replace(this, "")

private fun between(value: Int, bounds: Pair<Int, Int>): Boolean =
        value in bounds.first..bounds.second

private fun digits(value: String, length: Int) = value.length == length && value.toIntOrNull() != null

fun requiredFieldsValidator(passport: Map<String, String>): Boolean {
    val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    return requiredFields.all { passport.keys.contains(it) }
            && passport.values.all { it.isNotEmpty() }
}

private fun parsePassports(input: String): List<Map<String, String>> = input.lines()
        .splitByEmptyLines()
        .map { toPassport(it) }

private fun toPassport(lines: List<String>) = lines.flatMap { line -> toPassportFields(line) }
        .toMap()

private fun toPassportFields(line: String): List<Pair<String, String>> = line.split(" ")
        .map { field ->
            val (key, value) = field.split(":")
            Pair(key.trim(), value.trim())
        }


private fun List<String>.splitByEmptyLines(): List<List<String>> {
    val result = mutableListOf<MutableList<String>>()
    for (line in this) {
        when {
            line.isEmpty() -> result.add(result.size, mutableListOf())
            result.isEmpty() -> result.add(0, mutableListOf(line))
            else -> result[result.size - 1].add(line)
        }
    }
    return result
}