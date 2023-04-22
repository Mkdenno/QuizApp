package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.databinding.ActivityQuizQuestionBinding

class QuizQuestionActivity : AppCompatActivity() , View.OnClickListener{
    private lateinit var binding: ActivityQuizQuestionBinding

    private var mCurrentPosition:Int =1
    private var mQuestionsList:ArrayList<Question>?=null
    private var mSelectedOptionPosition: Int =0
    private var mCorrectAnswer: Int=0

    var mUserName: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserName=intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()
//        Log.i("Questions size", "${questionsList.size}")

        setQuestion()
        binding.optionOne.setOnClickListener(this)
        binding.optionTwo.setOnClickListener(this)
        binding.optionThree.setOnClickListener(this)
        binding.optionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

    }
    private fun setQuestion(){

        val question= mQuestionsList!![mCurrentPosition -1]

        defaultOptionView()
        if(mCurrentPosition == mQuestionsList!!.size){
            binding.btnSubmit.text ="FINISH"
        }
        else{
            binding.btnSubmit.text="SUBMIT"
        }

        binding.progressBar.progress=mCurrentPosition
        binding.tvProgress.text= "$mCurrentPosition" + "/" + binding.progressBar.max
        binding.tvquestion.text=question.question
        binding.ivimage.setImageResource(question.image)
        binding.optionOne.text=question.optionOne
        binding.optionTwo.text=question.optionTwo
        binding.optionThree.text=question.optionThree
        binding.optionFour.text=question.optionFour
    }
    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        options.add(0, binding.optionOne)
        options.add(1, binding.optionTwo)
        options.add(2, binding.optionThree)
        options.add(3, binding.optionFour)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background =ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 ->{
                binding.optionOne.background=ContextCompat.getDrawable(this, drawableView)
            }
            2 ->{
                binding.optionTwo.background=ContextCompat.getDrawable(this, drawableView)
            }
            3 ->{
                binding.optionThree.background=ContextCompat.getDrawable(this, drawableView)
            }
            4 ->{
                binding.optionFour.background=ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.optionOne -> {
                selectedOptionView(binding.optionOne, 1)
            }
            R.id.optionTwo -> {
                selectedOptionView(binding.optionTwo, 2)
            }
            R.id.optionThree -> {
                selectedOptionView(binding.optionThree, 3)
            }
            R.id.optionFour -> {
                selectedOptionView(binding.optionFour, 4)
            }
            R.id.btn_submit -> {
             if(mSelectedOptionPosition==0){
                 mCurrentPosition ++

                 when {
                     mCurrentPosition <= mQuestionsList!!.size -> {
                         setQuestion()
                     }
                     else ->{
                         val intent= Intent(this,ResultsActivity::class.java )

                         intent.putExtra(Constants.USER_NAME, mUserName)
                             intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer)
                         intent.putExtra(Constants.TOTAL_QUESTION, mQuestionsList!!.size)
                         startActivity(intent)
                     }
                 }
             }
                else{
                    val question=mQuestionsList?.get(mCurrentPosition -1)
                 if(question!!.correctAnswer != mSelectedOptionPosition){
                     answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                 }
                 else{
                     mCorrectAnswer ++
                 }
                 answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                 if(mCurrentPosition == mQuestionsList!!.size){
                     binding.btnSubmit.text="FINISH"

                 }
                 else{
                     binding.btnSubmit.text="GO TO NEXT QUESTION"
                 }
                 mSelectedOptionPosition = 0
             }
            }


        }
    }

    private fun selectedOptionView(tv: TextView , selectedOptionNum: Int){
        defaultOptionView()
        mSelectedOptionPosition=selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background =ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }
}