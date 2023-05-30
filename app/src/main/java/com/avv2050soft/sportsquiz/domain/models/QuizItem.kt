package com.avv2050soft.sportsquiz.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_items")
data class QuizItem(
    @PrimaryKey
    val id: Int,
    val question: String,
    val answerOne: String,
    val answerTwo: String,
    val answerThree: String
)
