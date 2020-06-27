package com.ulusoyapps.tictactoe.cache.entity

import android.os.Parcelable
import androidx.annotation.IntRange
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * A Coordinate on a 3x3 TicTacToe grid table:
 *
 *  (0,0) | (0,1) | (0,2)
 *  (1,0) | (1,1) | (1,2)
 *  (2,0) | (2,1) | (2,2)
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class CachedCoordinate(
    @IntRange(from = 0, to = 2) val row: Int,
    @IntRange(from = 0, to = 2) val column: Int
): Parcelable