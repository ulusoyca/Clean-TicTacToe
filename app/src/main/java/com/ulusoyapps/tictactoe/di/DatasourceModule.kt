/*
 * Copyright 2020 Cagatay Ulusoy (Ulus Oy Apps). All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ulusoyapps.tictactoe.di

import com.ulusoyapps.tictactoe.datasource.datasources.gamestatus.GameStatusDataRepository
import com.ulusoyapps.tictactoe.datasource.datasources.playermove.PlayerMoveDataRepository
import com.ulusoyapps.tictactoe.datasource.datasources.statistics.StatisticsDataRepository
import com.ulusoyapps.tictactoe.domain.repository.GameStatusRepository
import com.ulusoyapps.tictactoe.domain.repository.PlayerMoveRepository
import com.ulusoyapps.tictactoe.domain.repository.StatisticsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DatasourceModule {
    // Use @Binds to tell Dagger which implementation it needs to use when providing an interface.
    @Binds
    abstract fun provideGameStatusRepository(gameStatusRepository: GameStatusDataRepository): GameStatusRepository

    @Binds
    abstract fun providePlayerMoveRepository(playerMoveRepository: PlayerMoveDataRepository): PlayerMoveRepository

    @Binds
    abstract fun provideStatisticsRepository(statisticsRepository: StatisticsDataRepository): StatisticsRepository
}
