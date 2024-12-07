fun main() {
    val input = getInput("day7.txt")!!.lines().filter { it.isNotEmpty() }

    var sum = 0L

    for (line in input) {
        val (test, nums) = line.split(":")
        val testI = test.toLong()
        val numbers = nums.trim().split(" ").map { it.toLong() }

        if (testNumbers(numbers, testI)) {
            sum += testI
        }
    }

    println(sum)
}

private fun add(
    a: Long,
    b: Long,
): Long = a + b

private fun mul(
    a: Long,
    b: Long,
): Long = a * b

private fun con(
    a: Long,
    b: Long,
): Long = "$a$b".toLong()

private fun testNumbers(
    nums: List<Long>,
    test: Long,
): Boolean {
    if (testNumbers(nums, test, ::con)) {
        return true
    }
    if (testNumbers(nums, test, ::add)) {
        return true
    }

    return testNumbers(nums, test, ::mul)
}

private fun testNumbers(
    nums: List<Long>,
    test: Long,
    operator: (a: Long, b: Long) -> Long,
): Boolean {
    if (nums.size == 1) return nums.first() == test
    val (a, b) = nums.take(2)

    if (testNumbers(listOf(operator(a, b)) + nums.drop(2), test, ::con)) return true
    if (testNumbers(listOf(operator(a, b)) + nums.drop(2), test, ::add)) return true
    return testNumbers(listOf(operator(a, b)) + nums.drop(2), test, ::mul)
}
