package com.kamran.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.kamran.assignment.databinding.ActivityMainBinding
import com.kamran.assignment.modals.QuestionModal
import com.kamran.assignment.utils.Dialog
import com.kamran.assignment.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.progressBar.max = mainViewModel.questions.size

        bindObservers(mainViewModel.questions)

        binding.nextOrFinishBtn.setOnClickListener {

            if (binding.radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Please Select Any Answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                saveSelectedAnswer()
            }

            if (mainViewModel.quesNoLiveData.value!! < mainViewModel.questions.size) {
                mainViewModel.showNextQuestion()
            } else mainViewModel.showSuccessDialog()
        }

        binding.prevBtn.setOnClickListener {

            saveSelectedAnswer()

            if (mainViewModel.quesNoLiveData.value!! == 1) {
                mainViewModel.showWarningDialog()
            } else mainViewModel.showPrevQuestion()
        }


    }

    private fun saveSelectedAnswer() {
        when {
            binding.option1.isChecked -> {
                mainViewModel.questions[mainViewModel.quesNoLiveData.value!! - 1].answer = 1
            }
            binding.option2.isChecked -> {
                mainViewModel.questions[mainViewModel.quesNoLiveData.value!! - 1].answer = 2
            }
            binding.option3.isChecked -> {
                mainViewModel.questions[mainViewModel.quesNoLiveData.value!! - 1].answer = 3
            }
            binding.option4.isChecked -> {
                mainViewModel.questions[mainViewModel.quesNoLiveData.value!! - 1].answer = 4
            }
        }
    }

    private fun bindObservers(questions: List<QuestionModal>) {

        mainViewModel.quesNoLiveData.observe(this) { quesNo ->
            binding.progressBar.progress = quesNo
            binding.progressText.text = "$quesNo".plus(" / ").plus("${questions.size}")

            showQuestion(quesNo, questions)

            if (quesNo == questions.size) binding.nextOrFinishBtn.text = "Finish"
            else binding.nextOrFinishBtn.text = "Next"

        }


        mainViewModel.showSuccessDialogLiveData.observe(this) {
            val successDialog = Dialog(this, "Quiz Completed").successDialog
            if (it) {
                successDialog.show()
                successDialog.setOnDismissListener {
                    mainViewModel.showSuccessDialog(flag = false)
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }
            } else {
                successDialog.dismiss()
            }
        }


        mainViewModel.showWarningDialogLiveData.observe(this) {
            val warningDialog = Dialog(this, "It is first question.").warningDialog
            if (it) {
                warningDialog.show()
                warningDialog.setOnDismissListener {
                    mainViewModel.showWarningDialog(flag = false)
                }
            } else {
                warningDialog.dismiss()
            }
        }


    }

    private fun showQuestion(quesNo: Int, questions: List<QuestionModal>) {

        binding.image.load(questions[quesNo - 1].image)

        binding.questionNo.text = "${questions[quesNo - 1].questionNo}".plus(".")
        binding.question.text = questions[quesNo - 1].question
        binding.option1.text = questions[quesNo - 1].optionOne
        binding.option2.text = questions[quesNo - 1].optionTwo
        binding.option3.text = questions[quesNo - 1].optionThree
        binding.option4.text = questions[quesNo - 1].optionFour


        when (questions[quesNo - 1].answer) {
            1 -> {
                binding.option1.isChecked = true
            }

            2 -> {
                binding.option2.isChecked = true
            }

            3 -> {
                binding.option3.isChecked = true
            }

            4 -> {
                binding.option4.isChecked = true
            }

            else -> binding.radioGroup.clearCheck()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}