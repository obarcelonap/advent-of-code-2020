package advent.of.code.twentytwenty

fun <T> identity(x: T): T = x

fun applyMask(value: Int, mask: String): Int {
    val fold = mask.mapIndexed { index, character ->
        when (character) {
            '1' -> fun(v: Int): Int { return v or shiftingLeft(mask.length - index - 1) }
            '0' -> fun(v: Int): Int { return v and shiftingLeft(mask.length - index - 1).inv() }
            else -> ::identity
        }
    }
            .fold(value, { v, f -> f(v) })

    println(Integer.toBinaryString(fold))
    return fold
}

private fun shiftingLeft(numberOfShifts: Int) = 1 shl numberOfShifts
