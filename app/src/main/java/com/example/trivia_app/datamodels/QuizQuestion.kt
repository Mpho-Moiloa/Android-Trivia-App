package com.example.trivia_app.datamodels

data class QuizQuestion(
    /**
     * [quizId] is `categoryId`.
     */
    val question: String,
    val quizId: Int,
    val answers: List<String>,
    val correctAnswer: String
)