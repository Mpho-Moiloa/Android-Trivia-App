package com.example.trivia_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trivia_app.datamodels.QuizQuestion

class QuizGameViewModel: ViewModel(){

    private val _question = MutableLiveData<QuizQuestion>()
    private val _currentQuestion = MutableLiveData<Int>()
    private val _score = MutableLiveData<Int>()
    private val _amountOfQuestions = MutableLiveData<Int>()
    private val _categoryQuestions = MutableLiveData<List<QuizQuestion>>()

    fun setupGame(quizId: Int){
        val newQuizQuestion = questions.filter { question -> question.quizId == quizId }
        _categoryQuestions.postValue(newQuizQuestion)
        _question.value = newQuizQuestion[0]
        _currentQuestion.value = 0
        _score.value = 0
        _amountOfQuestions.value = 5
    }

   val question: LiveData<QuizQuestion> = _question
   val currentQuestion: LiveData<Int> = _currentQuestion
    val score: LiveData<Int> = _score
    val amountOfQuestion: LiveData<Int> = _amountOfQuestions

    fun updateQuestion(index: Int) {
        _question.value = _categoryQuestions.value?.get(index.plus(1))
        _currentQuestion.value = index.plus(1)
    }

    fun checkQuestion(answer: Int){
        val validAnswer: String? = question.value?.correctAnswer
        val submittedAnswer: String? = question.value?.answers?.get(answer)
        if(submittedAnswer == validAnswer){
            _score.value = score.value?.plus(10)
        }else{}
    }


    val quizChoices = listOf(
        QuizItem(1, "Pizza", "A dish of Italian origin consisting of a flattened disk of bread dough and toppings"),
        QuizItem(2, "Sports", "A physical contests pursued for the goals and challenges they entail."),
        QuizItem(3, "Cars", "a wheeled motor vehicle used for transportation."),
        QuizItem(4, "Animals", "understand animals and the animal kingdom")

    )

    private val questions = listOf<QuizQuestion>(
        QuizQuestion("What is the most popular form of crust worldwide?", 1, listOf("Thin crust", "Cheese-stuffed crust", "Deep dish"), "Thin crust"),
        QuizQuestion("When was Domino’s Pizza founded?", 1, listOf("1960", "1970", "1980"), "1960"),
        QuizQuestion("Which toppings are on a Quattro Stagioni pizza?", 1, listOf("Prosciutto, mushrooms, rocket and black olives", "Cooked ham, mushrooms, artichokes and black olives", "Cooked ham, rocket, artichokes and black olives"), "Cooked ham, mushrooms, artichokes and black olives"),
        QuizQuestion("How many pizzas are sold in the world each year?", 1, listOf("5 million", "50 million", "5 billion"), "5 billion"),
        QuizQuestion("Which country eats the most pizza (per head)", 1, listOf("Italy", "USA", "Norway"), "Norway"),

        QuizQuestion("The Olympics are held every how many years?", 2, listOf("2 years", "3 years", "4 years"), "4 years"),
        QuizQuestion("Who has won more tennis grand slam titles, Venus Williams or Serena Williams?", 2, listOf("Serena Williams", "Venus Williams", "Its a Tie"), "Serena Williams"),
        QuizQuestion("Which boxer fought against Muhammad Ali and won?", 2, listOf("George Foreman", "Joe Frazier", "Sonny Liston"), "Joe Frazier"),
        QuizQuestion("Which golf tournament did Tiger Woods win by 12 strokes cementing his first-ever major championship win?", 2, listOf("Ryder Cup", "PGA Tour", "The Masters"), "The Masters"),
        QuizQuestion("In what game is “love” a score?", 2, listOf("Tennis", "Basketball", "Cricket"), "Tennis"),

        QuizQuestion("Which animal features in the logo for Lamborghini?", 3, listOf("A horse", "A bull", "A lion"), "A bull"),
        QuizQuestion("What was the original color for all Ferrari models?", 3, listOf("Red", "Yellow", "Green"), "Red"),
        QuizQuestion("Which iconic car manufacturer also made airplane engines?", 3, listOf("Tata Motors", "Bentley", "Rolls Royce"), "Rolls Royce"),
        QuizQuestion("What is the world’s all-time best selling car?", 3, listOf("Opel Astra", "Toyota Corolla", "Audi A4"), "Toyota Corolla"),
        QuizQuestion("What is the car driven by Mr Bean, the hapless character played by Rowan Atkinson?", 3, listOf("A Toyota", "A BMW", "A MINI"), "A MINI"),

        QuizQuestion("What is the slowest animal on land?", 4, listOf("The snail", "The sloth", "The turtle"), "The sloth"),
        QuizQuestion("What is the fastest land animal?", 4, listOf("The cheetah", "The leopard", "The springbok"), "The cheetah"),
        QuizQuestion("What is a female elephant called?", 4, listOf("A female elephant", "A cow", "A giraffe"), "A cow"),
        QuizQuestion("How many feathers are there in a peacock’s tail?", 4, listOf("200 on average", "between 400 and 700", "over 9000"), "200 on average"),
        QuizQuestion("How many arms do squids have?", 4, listOf("4", "10", "12"), "10")

    )
}