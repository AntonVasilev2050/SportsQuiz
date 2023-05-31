package com.avv2050soft.sportsquiz.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_items")
data class QuizItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val question: String,
    val answerOne: String,
    val answerTwo: String,
    val answerThree: String,
    val answerFour: String,
    val imageUrl: String
//    "android.resource://com.avv2050soft.sportsquiz/drawable/image_name"
)
