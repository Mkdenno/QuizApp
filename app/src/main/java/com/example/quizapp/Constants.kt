package com.example.quizapp

object Constants{
    const val USER_NAME: String ="user_name"
    const val TOTAL_QUESTION: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionsList =ArrayList<Question>()

        val que1=Question(1, "Which Country Does this Flag Belong to?",
        R.drawable.kenya1, "Kenya", "Australia", "Malawi", "Uganda",1
            )
        questionsList.add(que1)

        val que2=Question(2, "Which Country Does this Flag Belong to?",
            R.drawable.nigeria, "Kenya", "Nigeria", "Algeria", "Malawi",2
        )
        questionsList.add(que2)


        val que3=Question(3, "Which Country Does this Flag Belong to?",
            R.drawable.libya, "Sudan", "Somalia", "Libya", "Senegal",3
        )
        questionsList.add(que3)


        val que4=Question(4, "Which Country Does this Flag Belong to?",
            R.drawable.uganda, "Togo", "Chad", "DRC", "Uganda",4
        )
        questionsList.add(que4)


        val que5=Question(5, "Which Country Does this Flag Belong to?",
            R.drawable.namibia, "Ethiopia", "Mali", "Morocco", "Namibia",4
        )
        questionsList.add(que5)


        val que6=Question(6, "Which Country Does this Flag Belong to?",
            R.drawable.zambia, "Zambia", "Zimbabwe", "Uganda", "Somalia",1
        )
        questionsList.add(que6)

        return questionsList
    }
}