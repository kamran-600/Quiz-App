package com.kamran.assignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamran.assignment.modals.QuestionModal

class MainViewModel : ViewModel() {


    val questions = listOf(
        QuestionModal(
            questionNo = 1,
            question = "A car travels at a speed of 60 km/h. How long will it take to cover a distance of 180 km?",
            optionOne = "2 hours",
            optionTwo = "2.5 hours",
            optionThree = "3 hours",
            optionFour = "4 hours"
        ),
        QuestionModal(
            questionNo = 2,
            question = "A train travels 120 km in 2 hours. What is its speed in km/h?",
            optionOne = "60 km/h",
            optionTwo = "70 km/h",
            optionThree = "50 km/h",
            optionFour = "55 km/h"
        ),
        QuestionModal(
            questionNo = 3,
            question = "If a person walks at a speed of 5 km/h, how much distance will he cover in 3 hours?",
            optionOne = "10 km",
            optionTwo = "15 km",
            optionThree = "20 km",
            optionFour = "25 km"
        ),
        QuestionModal(
            questionNo = 4,
            question = "A cyclist covers a distance of 45 km in 3 hours. What is his average speed?",
            optionOne = "10 km/h",
            optionTwo = "12 km/h",
            optionThree = "15 km/h",
            optionFour = "18 km/h"
        ),
        QuestionModal(
            questionNo = 5,
            question = "Two cities are 300 km apart. A car travels from City A to City B at a speed of 75 km/h. How long will the journey take?",
            optionOne = "3 hours",
            optionTwo = "4 hours",
            optionThree = "5 hours",
            optionFour = "6 hours"
        )
    )

    private val _quesNoLiveData = MutableLiveData(1)
    val quesNoLiveData: LiveData<Int> get() = _quesNoLiveData

    private val _showSuccessDialogLiveData = MutableLiveData(false)
    val showSuccessDialogLiveData: LiveData<Boolean> get() = _showSuccessDialogLiveData


    private val _showWarningDialogLiveData = MutableLiveData(false)
    val showWarningDialogLiveData: LiveData<Boolean> get() = _showWarningDialogLiveData



    fun showNextQuestion() {
        _quesNoLiveData.value = (_quesNoLiveData.value ?: 1) + 1
    }


    fun showPrevQuestion() {
        _quesNoLiveData.value = (_quesNoLiveData.value ?: 1) - 1
    }


    fun showSuccessDialog(flag: Boolean = true) {
        _showSuccessDialogLiveData.value = flag
    }


    fun showWarningDialog(flag: Boolean = true) {
        _showWarningDialogLiveData.value = flag
    }




}