package com.kire.market_place_android.presentation.util.search

/**
 * Calculates the Levenshtein ratio between two character sequences.
 *
 * @param lhs The first character sequence.
 * @param rhs The second character sequence.
 * @return The Levenshtein ratio between the two sequences.
 * @author Aleksey Timko (de4ltt)
 */
fun levenshteinRatio(lhs: CharSequence, rhs: CharSequence): Double {
    val maxLen = maxOf(lhs.length, rhs.length)
    if (maxLen == 0) {
        return 1.0
    }
    val distance = levenshteinDistance(lhs, rhs)
    return 1.0 - distance.toDouble() / maxLen
}