package com.example.exam_4x4tictactoe

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PlayerView(application: Application) : AndroidViewModel(application) {

    private val  repository: PlayerRepository
    val allPlayersLive: LiveData<List<Player>>
    //co-routine
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val playerDAO = PlayerRoomDatabase.getDatabase(application.applicationContext)
            .playerDao()
        repository = PlayerRepository(playerDAO)
        allPlayersLive = repository.allPlayers
    }

    fun insert(player: Player) = scope.launch(Dispatchers.IO) {
        repository.insert(player)

    }

    fun delete() = scope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun get() = scope.launch(Dispatchers.IO){
        repository.getAll()
    }
}
