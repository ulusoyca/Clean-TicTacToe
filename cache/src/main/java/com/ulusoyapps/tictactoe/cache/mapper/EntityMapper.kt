package com.ulusoyapps.tictactoe.cache.mapper

interface EntityMapper<C, E> {
    fun mapFromDomainEntity(type: C): E
    fun mapToDomainEntity(type: E): C
    fun mapFromDomainEntityList(type: List<C>): List<E>
    fun mapToDomainEntityList(type: List<E>): List<C>
}
