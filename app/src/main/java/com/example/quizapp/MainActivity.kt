package com.example.quizapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.setDecorFitsSystemWindows(false)



        binding.btnStart.setOnClickListener{
           if (binding.etName.text.toString().isEmpty()){
               Toast.makeText( this, "Please Enter your Name", Toast.LENGTH_SHORT).show()
           }
            else{
                val intent = Intent(this, QuizQuestionActivity::class.java)
               intent.putExtra(Constants.USER_NAME, binding.etName.text.toString())
               startActivity(intent)
               finish()
           }
        }
    }
}