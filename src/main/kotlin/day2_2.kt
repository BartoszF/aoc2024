fun main() {
    val input =
        getInput("day2.txt")!!.lines().map {
            it
                .trim()
                .split("\\s+".toRegex())
                .map { it.toInt() }
                .toMutableList()
        }

    val count =
        input.count count@{ report ->
            val result = checkReport(report)
            if (!result) {
                val dampedReports = dampReport(report)
                val dampedResults = dampedReports.count { checkReport(it) }
                return@count dampedResults > 0
            }
            true
        }

    println(count)
}

fun dampReport(report: MutableList<Int>): List<MutableList<Int>> =
    List(report.size) { index ->
        val newList = report.toMutableList()
        newList.removeAt(index)
        newList
    }
