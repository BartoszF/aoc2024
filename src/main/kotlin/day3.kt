fun main() {
    val input = getInput("day3_test.txt")!!.lines()

    val mulRegex = "mul\\(([0-9]+),([0-9]+)\\)".toRegex()

    var sum = 0

    input.forEach { line ->
        val matches = mulRegex.findAll(line)
        sum += matches.sumOf { it.groups[1]!!.value.toInt() * it.groups[2]!!.value.toInt() }
    }

    println(sum)
}
