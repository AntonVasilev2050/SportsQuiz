package com.avv2050soft.sportsquiz.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player(

    @PrimaryKey(autoGenerate = true)
    val  id: Int,
    val name: String,
    val gameScore: Int
)
