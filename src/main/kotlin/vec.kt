import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

data class Vec(
    val x: Int,
    val y: Int,
) {
    operator fun plus(other: Vec): Vec = Vec(x + other.x, y + other.y)

    operator fun minus(other: Vec): Vec = Vec(x - other.x, y - other.y)

    operator fun div(n: Int): Vec = Vec(x / n, y / n)

    operator fun times(n: Int): Vec = Vec(x * n, y * n)

    fun rev(): Vec = Vec(-x, -y)

    fun dist(other: Vec): Double = sqrt((other.x - x).toDouble().pow(2) + (other.y - y).toDouble().pow(2))

    fun diff(other: Vec): Vec = Vec(abs(other.x - x), abs(other.y - y))

    fun inBounds(
        w: Int,
        h: Int,
    ): Boolean = x in 0..<w && y in 0..<h

    fun copy(): Vec = Vec(x, y)

    companion object {
        val ZERO = Vec(0, 0)
        val UP = Vec(0, -1)
        val DOWN = Vec(0, 1)
        val LEFT = Vec(-1, 0)
        val RIGHT = Vec(1, 0)
    }
}
