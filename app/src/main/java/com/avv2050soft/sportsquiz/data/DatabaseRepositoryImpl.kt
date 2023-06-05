package com.avv2050soft.sportsquiz.data

import android.content.Context
import com.avv2050soft.sportsquiz.domain.models.Player
import com.avv2050soft.sportsquiz.domain.models.QuizItem
import com.avv2050soft.sportsquiz.domain.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    context: Context
) : DatabaseRepository {

    private val db = QuizDatabase.getInstance(context)

    override suspend fun getAllQuizItemsFromDb(): List<QuizItem> {
        return db.quizItemsDao().getAllQuizItemsFromDb()
    }

    override suspend fun getQuizItemsFromDb(count: Int): List<QuizItem> {
        return db.quizItemsDao().getQizItemsFromDb(count)
    }

    override suspend fun insertQuizItem(quizItem: QuizItem) {
        db.quizItemsDao().insertQuizItem(quizItem)
    }

    override suspend fun getAllPlayersFromDb(): List<Player> {
        return db.playersDao().getAllPlayersFromDb()
    }

    override suspend fun insertPlayer(player: Player) {
        db.playersDao().insertPlayer(player)
    }
}