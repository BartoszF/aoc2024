fun main() {
    val input = getInput("day2.txt")!!.lines().map { it.trim().split("\\s+".toRegex()).map { it.toInt() } }

    val count =
        input.count count@{ report ->
            checkReport(report)
        }

    println(count)
}

fun checkReport(report: List<Int>): Boolean {
    if (report[0] == report[1]) {
        return false
    }

    val inc = report[0] < report[1]

    report.forEachIndexed { index, _ ->
        if (index != report.size - 1) {
            if (report[index] > report[index + 1] && inc) {
                return false
            }

            if (report[index] < report[index + 1] && !inc) {
                return false
            }

            val diff = report[index].diff(report[index + 1])
            if (diff < 1 || diff > 3) {
                return false
            }
        }
    }

    return true
}
