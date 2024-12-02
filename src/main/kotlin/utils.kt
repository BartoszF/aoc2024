import java.lang.Integer.max
import java.lang.Integer.min

fun getInput(name: String): String? = {}.javaClass.getResource("/$name")?.readText()

fun <T> Sequence<T>.repeat() = generateSequence(this) { it }.flatten()

fun Int.diff(other: Int): Int = max(this, other) - min(this, other)
