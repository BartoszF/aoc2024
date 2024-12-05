import java.util.*

fun main() {
    val input = getInput("day5.txt")!!.lines()
    val split = input.indexOfFirst { it.isEmpty() }

    val ordering = mutableMapOf<String, MutableList<String>>()
    input.subList(0, split).map {
        val (x, y) = it.split("|")
        if (!ordering.containsKey(x)) ordering[x] = mutableListOf()
        ordering[x]!!.add(y)
    }

    val updates = input.subList(split + 1, input.size).map { it.split(",") }
    val wrong = mutableListOf<MutableList<String>>()

    var sum = 0
    for (update in updates) {
        val result = checkUpdate(update, ordering)
        if (result == 0) {
            wrong.add(update.toMutableList())
        } else {
            sum += result
        }
    }

    println("PART 1")
    println(sum)

    sum = 0
    for (w in wrong) {
        sum += sortWrong(w, ordering)
    }

    println("PART 2")
    println(sum)
}

fun sortWrong(
    wrong: MutableList<String>,
    ordering: MutableMap<String, MutableList<String>>,
): Int {
    loop@ while (true) {
        val stack = Stack<String>()

        for (n in wrong) {
            val before = ordering[n]!!
            val t = before.firstOrNull { stack.contains(it) }
            if (t != null) {
                val from = wrong.indexOf(n)
                val to = wrong.indexOf(t)

                wrong[from] = t
                wrong[to] = n
                continue@loop
            }
            stack.push(n)
        }

        return wrong[wrong.size / 2].toInt()
    }
}

private fun checkUpdate(
    update: List<String>,
    ordering: Map<String, List<String>>,
): Int {
    val stack = Stack<String>()

    for (n in update) {
        val before = ordering[n]!!
        if (before.any { stack.contains(it) }) {
            return 0
        }
        stack.push(n)
    }

    return update[update.size / 2].toInt()
}
