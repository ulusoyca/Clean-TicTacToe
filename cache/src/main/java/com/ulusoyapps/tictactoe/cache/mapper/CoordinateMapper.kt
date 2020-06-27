package com.ulusoyapps.tictactoe.cache.mapper

import com.ulusoyapps.tictactoe.cache.entity.CachedCoordinate
import com.ulusoyapps.tictactoe.domain.entitiy.Coordinate
import javax.inject.Inject

class CoordinateMapper
@Inject constructor() : EntityMapper<Coordinate, CachedCoordinate> {
    override fun mapFromDomainEntity(type: Coordinate): CachedCoordinate {
        return CachedCoordinate(
            row = type.row,
            column = type.column
        )
    }

    override fun mapToDomainEntity(type: CachedCoordinate): Coordinate {
        return Coordinate(
            row = type.row,
            column = type.column
        )
    }

    override fun mapFromDomainEntityList(type: List<Coordinate>): List<CachedCoordinate> {
        return type.map { mapFromDomainEntity(it) }
    }

    override fun mapToDomainEntityList(type: List<CachedCoordinate>): List<Coordinate> {
        return type.map { mapToDomainEntity(it) }
    }
}