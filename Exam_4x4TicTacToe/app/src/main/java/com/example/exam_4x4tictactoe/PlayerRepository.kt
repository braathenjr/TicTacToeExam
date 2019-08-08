package com.example.exam_4x4tictactoe

import android.arch.lifecycle.LiveData

import android.support.annotation.WorkerThread

class PlayerRepository(private val playerDao: PlayerDao) {

    val allPlayers: LiveData<List<Player>> = playerDao.getAllPlayersLive()

    @WorkerThread
    suspend fun insert(player: Player) {
        playerDao.insert(player)
    }
}