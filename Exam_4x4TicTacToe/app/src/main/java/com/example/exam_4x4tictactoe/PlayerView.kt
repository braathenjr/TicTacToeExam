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

    private val repository: PlayerRepository
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    val allPlayers: LiveData<List<Player>>

    init {
        val playersDao = PlayerRoomDatabase.getDatabase(application).playerDao()
        repository = PlayerRepository(playersDao)
        allPlayers = repository.allPlayers
    }
/*
    fun insert(player: Player) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(player)
    }*/
    fun insert(player: Player) = scope.launch(Dispatchers.IO) {
        repository.insert(player)
    }
}
