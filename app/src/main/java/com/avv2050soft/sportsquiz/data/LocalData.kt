package com.avv2050soft.sportsquiz.data

import com.avv2050soft.sportsquiz.R
import com.avv2050soft.sportsquiz.domain.models.QuizItem
import com.avv2050soft.sportsquiz.domain.models.WallpaperItem

object LocalData {

    val wallpaperItemList = listOf<WallpaperItem>(
        WallpaperItem(
            R.drawable.wallpaper_butterfly,
            40
        ),
        WallpaperItem(
            R.drawable.wallpaper_cubes,
            60
        ),
        WallpaperItem(
            R.drawable.wallpaper_leaves,
            5
        ),
        WallpaperItem(
            R.drawable.wallpaper_water,
            15
        ),
        WallpaperItem(
            R.drawable.wallpaper_water_leaves,
            10
        ),
        WallpaperItem(
            R.drawable.wallpaper_windows_95,
            75
        ),
    )

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
        )
    )
}