package com.example.exam_4x4tictactoe

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import java.io.Serializable

@Entity(tableName = "Player_table")
class Player(@PrimaryKey var name: String,
             @ColumnInfo(name = "wins")var wins: Int) : Serializable
