package com.kire.market_place_android.presentation.util.search

/**
 * Calculates the Levenshtein distance between two character sequences.
 *
 * @param lhs The first character sequence.
 * @param rhs The second character sequence.
 * @return The Levenshtein distance between the two sequences.
 * @author Aleksey Timko (de4ltt)
 */
internal fun levenshteinDistance(lhs: CharSequence, rhs: CharSequence): Int {
    val lhsLength = lhs.length
    val rhsLength = rhs.length
    val distance = Array(lhsLength + 1) { IntArray(rhsLength + 1) }

    for (i in 0..lhsLength) {
        distance[i][0] = i
    }
    for (j in 0..rhsLength) {
        distance[0][j] = j
    }

    for (i in 1..lhsLength) {
        for (j in 1..rhsLength) {
            val cost = if (lhs[i - 1] == rhs[j - 1]) 0 else 1
            distance[i][j] = minOf(
                distance[i - 1][j] + 1,
                distance[i][j - 1] + 1,
                distance[i - 1][j - 1] + cost
            )
        }
    }
    return distance[lhsLength][rhsLength]
}
