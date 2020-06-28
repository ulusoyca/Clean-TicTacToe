package com.ulusoyapps.tictactoe.cache.entity

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
class CachedStatistics(
    val win: Int = 0,
    val lose: Int = 0,
    val draw: Int = 0
): Parcelable