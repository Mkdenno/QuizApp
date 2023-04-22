package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityResultsBinding

class ResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username=intent.getStringExtra(Constants.USER_NAME)
        binding.tvName.text=username

        val totalQuestion=intent.getIntExtra(Constants.TOTAL_QUESTION ,0)
        val correctAnswer =intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        binding.tvScore.text="Your Score is $correctAnswer out of ${totalQuestion}"

        binding.btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}