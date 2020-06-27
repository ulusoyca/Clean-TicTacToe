package com.ulusoyapps.tictactoe.cache.mapper

import com.ulusoyapps.tictactoe.cache.entity.CachedMoves
import com.ulusoyapps.tictactoe.domain.entitiy.Moves
import javax.inject.Inject

class MovesMapper
@Inject constructor(
    private val coordinateMapper: CoordinateMapper
) : EntityMapper<Moves, CachedMoves> {

    override fun mapFromDomainEntity(type: Moves): CachedMoves {
        return CachedMoves(
            playerMoves = coordinateMapper.mapFromDomainEntityList(type.playerMoves),
            computerMoves = coordinateMapper.mapFromDomainEntityList(type.computerMoves)
        )
    }

    override fun mapToDomainEntity(type: CachedMoves): Moves {
        return Moves(
            playerMoves = coordinateMapper.mapToDomainEntityList(type.playerMoves),
            computerMoves = coordinateMapper.mapToDomainEntityList(type.computerMoves)
        )
    }

    override fun mapFromDomainEntityList(type: List<Moves>): List<CachedMoves> {
        return type.map { mapFromDomainEntity(it) }
    }

    override fun mapToDomainEntityList(type: List<CachedMoves>): List<Moves> {
        return type.map { mapToDomainEntity(it) }
    }


}