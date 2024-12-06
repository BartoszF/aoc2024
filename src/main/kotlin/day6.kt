fun main() {
    var guardPosition = Vec(0, 0)
    var startingGuardPosition = Vec(0, 0)
    var guardDir: Vec
    var startingGuardDir = Vec(0, 0)
    val input =
        getInput("day6.txt")!!
            .lines()
            .mapIndexed { y, s ->
                s.mapIndexed { x, c ->
                    when (c) {
                        '#' -> true
                        '.' -> false
                        else -> {
                            when (c) {
                                '^' -> startingGuardDir = Vec.UP
                                'v' -> startingGuardDir = Vec.DOWN
                                '<' -> startingGuardDir = Vec.LEFT
                                '>' -> startingGuardDir = Vec.RIGHT
                            }
                            startingGuardPosition = Vec(x, y)
                            false
                        }
                    }
                }
            }

    val width = input[0].size
    val height = input.size

    val visitedTiles = mutableSetOf<Vec>(guardPosition)

    guardPosition = startingGuardPosition.copy()
    guardDir = startingGuardDir.copy()

    while (true) {
        val next = guardPosition + guardDir
        if (next.x < 0 || next.x >= width || next.y < 0 || next.y >= height) break

        if (input[next.y][next.x]) {
            guardDir = rotateClockwise(guardDir)
        } else {
            guardPosition += guardDir
            visitedTiles.add(guardPosition)
        }
    }

    println("PART 1")
    println(visitedTiles.size)

    /*for (y in 0 until height) {
        for (x in 0 until width) {
            val v = Vec(x, y)
            if (visitedTiles.contains(v)) {
                print("X")
            } else if (input[y][x]) {
                print("#")
            } else {
                print(".")
            }
        }
        println()
    }*/

    var validObstacles = 0

    for (obstacle in visitedTiles) {
        guardPosition = startingGuardPosition.copy()
        guardDir = startingGuardDir.copy()
        val updatedMap = input.map { it.toMutableList() }.toMutableList()
        updatedMap[obstacle.y][obstacle.x] = true
        val positions = mutableSetOf<Pair<Vec, Vec>>(Pair(guardPosition, guardDir))
        while (true) {
            val next = guardPosition + guardDir
            if (next.x < 0 || next.x >= width || next.y < 0 || next.y >= height) break

            if (updatedMap[next.y][next.x]) {
                guardDir = rotateClockwise(guardDir)
            } else {
                guardPosition += guardDir
                if (positions.contains(Pair(guardPosition, guardDir))) {
                    validObstacles++
                    break
                }
                positions.add(Pair(guardPosition, guardDir))
            }
        }
    }

    println("PART 2")
    println(validObstacles)
}

fun rotateClockwise(v: Vec): Vec =
    when (v) {
        Vec.LEFT -> Vec.UP
        Vec.UP -> Vec.RIGHT
        Vec.RIGHT -> Vec.DOWN
        Vec.DOWN -> Vec.LEFT
        else -> error("Shouldn't happen!")
    }
