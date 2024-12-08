import kotlin.streams.toList

fun main() {
    val input =
        getInput("day8.txt")!!.lines().filter { it.isNotEmpty() }.map {
            it
                .chars()
                .toList()
                .map { it.toChar() }
        }

    val height = input.size
    val width = input[0].size

    val antennas = mutableMapOf<Char, MutableList<Vec>>()

    for (y in 0..<height) {
        for (x in 0..<width) {
            val vec = Vec(x, y)
            val ch = input[y][x]

            if (ch == '.') continue
            if (!antennas.containsKey(ch)) {
                antennas[ch] = mutableListOf()
            }

            antennas[ch]!!.add(vec)
        }
    }

    val antinodes = mutableSetOf<Vec>()

    for (ch in antennas.keys) {
        val nodes = antennas[ch]!!

        val pairs =
            nodes.flatMapIndexed { index, v ->
                nodes.slice(index + 1..<nodes.size).map { v2 -> Pair(v, v2) }.filter { it.first != it.second }
            }

        println("$ch $pairs")

        for ((n1, n2) in pairs) {
            var a1 = n1 // + (n1 - n2)
            var a2 = n2 // + (n2 - n1)

            while (a1.inBounds(width, height)) {
                antinodes.add(a1)
                a1 += (n1 - n2)
            }

            while (a2.inBounds(width, height)) {
                antinodes.add(a2)
                a2 += (n2 - n1)
            }

            // PART 1
//            if (a1.inBounds(width, height)) {
//                antinodes.add(a1)
//            }

//            if (a2.inBounds(width, height)) {
//                antinodes.add(a2)
//            }
        }
    }

    println(antinodes)

    for (y in 0..<height) {
        for (x in 0..<width) {
            if (antinodes.contains(Vec(x, y))) {
                print("#")
            } else {
                print(input[y][x])
            }
        }
        println()
    }

    println(antinodes.size)
}
