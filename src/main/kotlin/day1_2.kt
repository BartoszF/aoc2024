import java.lang.Integer.max
import java.lang.Integer.min

fun main() {
    val input =
        getInput("day1_test.txt")!!.lines().filter { it.isNotEmpty() }.map { it.trim().split("\\s+".toRegex()) }
            .map { it[0] to it[1] }
    val left = input.map { it.first.toInt() }.toMutableList()
    val right = input.map { it.second.toInt() }.toMutableList()

    assert(left.size == right.size)

    var sum = 0;

    while (left.size > 0) {
        val leftMin = left.min()
        left.remove(leftMin)
        val rightMin = right.min()
        right.remove(rightMin)

        val distance = max(leftMin, rightMin) - min(leftMin, rightMin)
        sum += distance
    }

    println(sum)
}
