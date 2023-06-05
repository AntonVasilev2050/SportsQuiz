package com.avv2050soft.sportsquiz.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avv2050soft.sportsquiz.domain.models.Player

@Dao
interface PlayersDao {

    @Query("SELECT * FROM players")
    suspend fun getAllPlayersFromDb(): List<Player>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: Player)
}