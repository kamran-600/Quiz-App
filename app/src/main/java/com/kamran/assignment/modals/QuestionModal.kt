package com.kamran.assignment.modals

data class QuestionModal(
    val questionNo: Int,
    val question: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    var answer : Int = 0
)
