package com.example.owitrivia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    //Setting vars
    private var totalQuestions = 0
    private var finalScore = 0
    private var mCorrectText: TextView? = null
    private var mPercentText: TextView? = null
    @SuppressLint("StringFormatMatches")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //Setting views var
        mCorrectText = findViewById<View>(R.id.correct_textview) as TextView
        mPercentText = findViewById<View>(R.id.percent_textview) as TextView

        //Getting intent values
        val intent = intent
        totalQuestions = intent.getIntExtra("totalQuestions", 0)
        finalScore = intent.getIntExtra("finalScore", 0)

        //Calculate score percentage
        val mPercentScore = finalScore * 100 / totalQuestions

        //Update views
        mPercentText!!.text = String.format("%s%%", Integer.toString(Integer.valueOf(mPercentScore)))
        //Getting text vars
        val final_score_txt = getString(R.string.txt_youve_got_x_out_of_x_correct,
            finalScore, totalQuestions)
        mCorrectText!!.text = final_score_txt
    }

    //Restart Quiz Button pressed
    fun restartBtn(view: View?) {
        super.onBackPressed()
    }
}