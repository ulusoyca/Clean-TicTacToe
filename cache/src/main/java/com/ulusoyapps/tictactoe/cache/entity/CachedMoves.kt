package com.ulusoyapps.tictactoe.cache.entity

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CachedMoves(
    val playerMoves: List<CachedCoordinate> = emptyList(),
    val computerMoves: List<CachedCoordinate> = emptyList()
) : Parcelable