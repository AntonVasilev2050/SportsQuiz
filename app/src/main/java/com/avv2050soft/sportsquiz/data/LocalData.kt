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
        ),
        QuizItem(
            11,
            "В каком году бокс стал легальным видом спорта? ",
            "1901",
            "1921",
            "1931",
            "В прошлом году",
            null
        ),
        QuizItem(
            12,
            "В какой вид спорта играют с этим мячом?",
            "Гандбол",
            "Волейбол",
            "Баскетбол",
            "Футбольный",
            "android.resource://com.avv2050soft.sportsquiz/drawable/quiz_pic_question_handball"
        ),
        QuizItem(
            13,
            "Какая страна выиграла ЧМ-2018?",
            "Франция",
            "Германия",
            "Аргентина",
            "СССР",
            null
        ),
        QuizItem(
            14,
            "Сколько лет шахматам?",
            "Примерано 1500 лет",
            "1428 лет, 3 месяца, 2 дня",
            "10000 лет",
            "200 лет",
            null
        ),
        QuizItem(
            15,
            "Какой диаметр баскетбольного кольца в дюймах?",
            "18",
            "1",
            "10",
            "20",
            null
        ),
        QuizItem(
            16,
            "Насколько велик бассейн олимпийских размеров в метрах?",
            "50 метров в длину и 25 метров в ширину",
            "25 метров в длину и 25 метров в ширину",
            "50 метров в длину и 20 метров в ширину",
            "50 метров в длину и 31 метр в ширину",
            null
        ),
        QuizItem(
            17,
            "В какой стране открытие Олимпийских Игр состоялось 8.8.2008г в 8ч 8мин 8сек?",
            "Китай",
            "Австралия",
            "Россия",
            "Великобритания",
            null
        ),
    )
}