import kotlin.streams.toList

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
            if (input[y][x] == 'A') {
                val v = Vec(x, y)
                var left = false
                var right = false
                if (lookNext(input, 'M', v, Vec(-1, -1)) != null) {
                    if (lookNext(input, 'S', v, Vec(1, 1)) != null) {
                        left = true
                    }
                } else {
                    if (lookNext(input, 'S', v, Vec(-1, -1)) != null) {
                        if (lookNext(input, 'M', v, Vec(1, 1)) != null) {
                            left = true
                        }
                    }
                }
                if (lookNext(input, 'M', v, Vec(-1, 1)) != null) {
                    if (lookNext(input, 'S', v, Vec(1, -1)) != null) {
                        right = true
                    }
                } else {
                    if (lookNext(input, 'S', v, Vec(-1, 1)) != null) {
                        if (lookNext(input, 'M', v, Vec(1, -1)) != null) {
                            right = true
                        }
                    }
                }

                if (left && right) found++
            }
        }
    }

    println(found)
}
