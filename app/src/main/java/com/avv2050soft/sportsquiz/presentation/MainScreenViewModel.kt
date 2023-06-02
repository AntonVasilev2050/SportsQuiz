package com.avv2050soft.sportsquiz.presentation

import androidx.lifecycle.ViewModel
import com.avv2050soft.sportsquiz.domain.models.QuizItem
import com.avv2050soft.sportsquiz.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: DatabaseRepository
) : ViewModel() {

    init {
//        viewModelScope.launch {
//            initDb()
//        }
    }

    suspend fun initDb() {
        quizItemList.forEach {
            repository.insertQuizItem(it)
        }
    }
}

val quizItemList = listOf<QuizItem>(
    QuizItem(
        1,
        "Сколько длится марафон?",
        "42.195 километров",
        "42.195 метров",
        "44.195 километров",
        "26 километров",
        null
    ),
    QuizItem(
        2,
        "Сколько игроков в бейсбольной команде?",
        "9 игроков",
        "11 игроков",
        "12 игроков",
        "10 игроков",
        null
    ),
    QuizItem(
        3,
        "В каком виде спорта у вас был бы тачдаун?",
        "Американский футбол",
        "Африканский футбол",
        "Бейсбол",
        "Рэгби",
        null
    ),
    QuizItem(
        4,
        "Какое настоящее имя Мухаммеда Али?",
        "Кассиус Клей",
        "Кассиус Плей",
        "Ашир Али",
        "Кассиус Али",
        null
    ),
    QuizItem(
        5,
        "В каком виде спорта играют с этим мячом?",
        "Бейсбол",
        "Сверчок",
        "Гольф ",
        "Теннис",
        "android.resource://com.avv2050soft.sportsquiz/drawable/quiz_pic_question_baseball"
    ),
    QuizItem(
        6,
        "В каком виде спорта играют с этим мячом?",
        "Сверчок",
        "Африканский футбол",
        "Бейсбол",
        "Ракетбол",
        "android.resource://com.avv2050soft.sportsquiz/drawable/quiz_pic_question_sverchek"
    ),
    QuizItem(
        7,
        "Как в боулинге называется 3 страйка подряд?",
        "Индюк",
        "Трипс",
        "Тристрайк",
        "Бинго",
        null
    ),
    QuizItem(
        8,
        "Сколько игроков в команде по мини-футболу?",
        "5",
        "4",
        "6",
        "2",
        null
    ),
    QuizItem(
        9,
        "Когда начали проводиться состязания атлетов в Олимпии?",
        "776 году до нашей эры",
        "776 году нашей эры",
        "1870 году нашей эры",
        "В прошлом году",
        null
    ),
    QuizItem(
        10,
        "Молодой спортсмен",
        "Юниор",
        "Джуниор",
        "Подросток",
        "Стажёр",
        null
    ),
)