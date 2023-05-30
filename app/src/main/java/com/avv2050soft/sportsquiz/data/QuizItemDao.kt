package com.avv2050soft.sportsquiz.data

import androidx.room.Dao
import androidx.room.Query
import com.avv2050soft.sportsquiz.domain.models.QuizItem

@Dao
interface QuizItemDao {

    @Query("SELECT * FROM quiz_items")
    suspend fun getAllQuizItemsFromDb(): List<QuizItem>

    @Query("SELECT * FROM quiz_items ORDER BY RANDOM() LIMIT  :count")
    suspend fun getQizItemsFromDb(count : Int): List<QuizItem>
}