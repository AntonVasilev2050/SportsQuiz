package com.avv2050soft.sportsquiz.domain.repository

import com.avv2050soft.sportsquiz.domain.models.QuizItem

interface DatabaseRepository {
    suspend fun getAllQuizItemsFromDb() : List<QuizItem>
    suspend fun getQuizItemsFromDb(count : Int) : List<QuizItem>
    suspend fun insertQuizItem(quizItem: QuizItem)
}