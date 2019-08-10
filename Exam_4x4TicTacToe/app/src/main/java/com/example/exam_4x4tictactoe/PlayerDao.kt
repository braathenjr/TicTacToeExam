package com.example.exam_4x4tictactoe

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface PlayerDao {

    @Query("SELECT * from player_table ORDER BY wins DESC")
    fun getAllPlayers(): List<Player>

    @Insert
    fun insert(player: Player)

    @Query("UPDATE player_table SET wins= wins + 1 WHERE name= :name")
    fun updateScore(name: String)

    @Query("DELETE FROM player_table")
    fun deleteAll()

    @Query("SELECT * FROM player_table")
    fun getAllPlayersLive() : LiveData<List<Player>>
}