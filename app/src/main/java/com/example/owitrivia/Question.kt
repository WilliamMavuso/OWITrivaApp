package com.example.owitrivia

//fun main(args: Array<String>) {
//
//    val question1 = Question.Question(question = "How did the chicken cross the road?", option1 = "To get to the other side", option2 = "Awe", option3 = "Nobody knows", option4 = "Sick", answerNr = 2);
//
//    println("Question = ${question1.}")
//    println("Age = ${person1.age}")
//}

class Question {
    var question: String? = null
    var option1: String? = null
    var option2: String? = null
    var option3: String? = null
    var option4: String? = null
    var answerNr: Int = 0

    class Question(question: String, option1: String, option2: String, option3: String, option4: String, answerNr: Int)
    { /*...*/ }
}