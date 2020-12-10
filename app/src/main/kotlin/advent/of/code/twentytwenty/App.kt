package advent.of.code.twentytwenty

class App {
    val greeting: String
        get() {
            return "Hello advent of code 2020!"
        }
}

fun main(args: Array<String>) {
    println(App().greeting)
}
