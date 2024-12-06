import kotlin.streams.toList

data class Vec(
    val x: Int,
    val y: Int,
) {
    operator fun plus(other: Vec): Vec = Vec(x + other.x, y + other.y)

    fun rev(): Vec = Vec(-x, -y)

    fun copy(): Vec = Vec(x, y)

    companion object {
        val ZERO = Vec(0, 0)
        val UP = Vec(0, -1)
        val DOWN = Vec(0, 1)
        val LEFT = Vec(-1, 0)
        val RIGHT = Vec(1, 0)
    }
}

val neighbours =
    listOf(
        Vec(-1, -1),
        Vec(0, -1),
        Vec(1, -1),
        Vec(-1, 0),
        Vec(1, 0),
        Vec(-1, 1),
        Vec(0, 1),
        Vec(1, 1),
    )

fun main() {
    val input =
        getInput("day4.txt")!!.lines().map {
            it
                .chars()
                .toList()
                .map { it.toChar() }
        }

    val height = input.size
    val width = input[0].size

    var found = 0

    for (y in 0..<height) {
        for (x in 0..<width) {
            if (input[y][x] == 'X') {
                val v = Vec(x, y)
                val next = lookAround(input, 'M', v)
                for (n in next) {
                    val f = lookNext(input, 'A', v + n, n)
                    if (f != null) {
                        if (lookNext(input, 'S', v + n + f, f) != null) {
                            found++
                        }
                    }
                }
            }
        }
    }

    println(found)
}

fun lookAround(
    input: List<List<Char>>,
    char: Char,
    v: Vec,
): List<Vec> {
    val results = mutableListOf<Vec>()
    for (n in neighbours) {
        val newV = v + n
        if (newV.x >= 0 && newV.x < input[0].size && newV.y >= 0 && newV.y < input.size && input[newV.y][newV.x] == char) {
            results.add(n)
        }
    }

    return results
}

fun lookNext(
    input: List<List<Char>>,
    char: Char,
    v: Vec,
    offset: Vec,
): Vec? {
    try {
        val newV = v + offset
        return if (input[newV.y][newV.x] == char) offset else null
    } catch (e: Throwable) {
        return null
    }
}
