package com.example.exam_4x4tictactoe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Player_table")
class Player(@PrimaryKey var name: String,
             @ColumnInfo(name = "wins")var wins: Int) : Serializable
