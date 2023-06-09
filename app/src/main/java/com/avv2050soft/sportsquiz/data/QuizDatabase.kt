package com.avv2050soft.sportsquiz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.avv2050soft.sportsquiz.domain.models.Player
import com.avv2050soft.sportsquiz.domain.models.QuizItem

@Database(entities = [QuizItem::class, Player::class],  version = 1, exportSchema = false)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun quizItemsDao(): QuizItemDao
    abstract fun playersDao(): PlayersDao

    companion object{
        private const val databaseName = "quiz"
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getInstance(context: Context): QuizDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    databaseName
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}