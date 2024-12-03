fun main() {
    val input = getInput("day3.txt")!!.lines()

    val mulRegex = "mul\\(([0-9]+),([0-9]+)\\)|do\\(\\)|don't\\(\\)".toRegex()

    var sum = 0
    var enabled = true

    input.forEach { line ->
        val matches = mulRegex.findAll(line)
        matches.forEach { match ->
            when {
                match.value.contains("mul(") -> {
                    if (enabled) {
                        sum += match.groups[1]!!.value.toInt() * match.groups[2]!!.value.toInt()
                    }
                }
                match.value.contains("do(") -> {
                    enabled = true
                }
                match.value.contains("don't(") -> {
                    enabled = false
                }
            }
        }
    }

    println(sum)
}
