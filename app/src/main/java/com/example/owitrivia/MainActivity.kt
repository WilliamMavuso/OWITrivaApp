package com.example.owitrivia


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //Setting vars
    private var mQuizImage: ImageView? = null
    private var mAnswer: String? = null
    private var mScore = 0
    private var mQuizNum = 1
    private var QuestionNum = 0
    private var cbClicks = 0
    private var mQuestionView: TextView? = null
    private var mQuizNumView: TextView? = null
    private var mNameView: TextView? = null
    private val mQuestions = Questions()
    val cbl = arrayOfNulls<CheckBox>(3)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setting views vars
        mQuestionView = findViewById<View>(R.id.question_textview) as TextView
        mQuizNumView = findViewById<View>(R.id.quiznum_textview) as TextView

        //Enter name info form
        validateName()

        //Init and update question
        updateQuestion()

        //Create Submit button listener
        val submit = findViewById<View>(R.id.button_submit) as Button
        submit.setOnClickListener { //Logics for EditText questionary
            if (mQuestions.getType(QuestionNum) === "edittext") {
                //Validate mAnswer with user's input
                //Also, make sure both value are in small letters to be compared
                if (mQuestions.getCorrectAnswer(QuestionNum).toLowerCase() == mAnswer) {
                    //If correct, score a point
                    mScore++

                    //Display correct answer in toast
                    displayToastCorrectAnswer()
                } else {
                    //Display wrong answer in toast
                    displayToastWrongAnswer()
                }
            }

            //Logics for Checkbox questionary
            if (mQuestions.getType(QuestionNum) === "checkbox") {
                //Iterate through checkboxes
                for (cb in cbl) {
                    if (cb!!.isChecked) //Concatenate answers
                        mAnswer += cb.text.toString()
                }
                //Validate answer
                val correctAns = mQuestions.getCorrectAnswer(QuestionNum)
                //Split correct answer by commma (,)
                val ckboxAnsList = correctAns.split(",".toRegex()).toTypedArray()
                var isCorrect = false

                //Loop through the correct answers and compare with user answer.
                //If any of the correct answer was checked, user scores.
                for (ci in ckboxAnsList.indices) {
                    if (ckboxAnsList[ci] == mAnswer) {
                        isCorrect = true
                    }
                }
                if (isCorrect) {
                    //If correct, score a point
                    mScore++
                }

                //Display correct answer in toast
                displayToastCorrectAnswer()
            }
            //Logics for Radio Button questionary
            if (mQuestions.getType(QuestionNum) === "radiobutton") {
                //Validate answer
                if (mQuestions.getCorrectAnswer(QuestionNum) == mAnswer) {
                    //If correct, score a point
                    mScore++

                    //Display correct answer in toast
                    displayToastCorrectAnswer()
                } else {
                    //Display wrong answer in toast
                    displayToastWrongAnswer()
                }
            }
            //Pause for second
            SystemClock.sleep(1000)

            //If question# is reached total question pool,
            //show score page using intent
            if (QuestionNum == mQuestions.length - 1) {
                //Finished all quiz, show scores
                val intent_result = Intent(
                    this@MainActivity,
                    ResultActivity::class.java
                )

                //Also include number of questions and score vars
                intent_result.putExtra("totalQuestions", mQuestions.length)
                intent_result.putExtra("finalScore", mScore)
                startActivity(intent_result)

                //Reset all vars back to init
                QuestionNum = 0
                mQuizNum = 1
                mScore = 0
            } else {
                //Continue to next questionary
                QuestionNum++
                mQuizNum++
            }
            //Update question content
            updateQuestion()
        }
    }

    //Make a toast message for number of checkbox clicked
    private fun displayToastCheckBoxClick(count: Int, q: Int) {
        //Get the string vars
        val maxAns = mQuestions.getMaxAnswer(q)
        val rb_click = getString(R.string.txt_toast_cb_clicks, count.toString(), maxAns.toString())
        //Display toast
        Toast.makeText(
            this@MainActivity,
            rb_click,
            Toast.LENGTH_SHORT
        ).show()
    }

    //Make a toast message for incorrect answer
    private fun displayToastWrongAnswer() {
        //Get the string vars
        val wrong_ans = getString(R.string.txt_toast_wrong_ans, mScore.toString())
        //Display toast
        Toast.makeText(
            this@MainActivity,
            wrong_ans,
            Toast.LENGTH_SHORT
        ).show()
    }

    //Make a toast message for correct answer
    private fun displayToastCorrectAnswer() {

        //Get the string vars
        val correct_ans = getString(R.string.txt_toast_correct_ans, mScore.toString())
        //Display toast
        Toast.makeText(
            this@MainActivity,
            correct_ans,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun updateQuestion() {
        //Clean up all data from answers_layout
        val answers_layout = findViewById<View>(R.id.answers_layout) as LinearLayout
        answers_layout.removeAllViews()
        mAnswer = ""

        //Set value to views
        mQuizNumView!!.text = mQuizNum.toString() + "/" + mQuestions.length.toString()
        mQuestionView!!.text = mQuestions.getQuestion(QuestionNum)

        //Check which type of question: checkbox or radio
        if (mQuestions.getType(QuestionNum) === "checkbox") {
            showCheckBoxAnswers(QuestionNum)
        }
        if (mQuestions.getType(QuestionNum) === "radiobutton") {
            showRadioButtonAnswers(QuestionNum)
        }
        if (mQuestions.getType(QuestionNum) === "edittext") {
            showEditText(QuestionNum)
        }


        //Show the question main image
        showMainImage(QuestionNum)

        //Scroll back to Top
        val sv = findViewById<View>(R.id.scrollview) as ScrollView
        sv.smoothScrollTo(0, 0)
    }

    private fun showMainImage(qnum: Int) {
        //Setting view var
        mQuizImage = findViewById<View>(R.id.quiz_image) as ImageView
        val img = mQuestions.getImage(QuestionNum)
        //Update image view
        mQuizImage!!.setImageResource(resources.getIdentifier(img, "drawable", packageName))
    }

    private fun showEditText(qnum: Int) {
        //Setting answers_layout (linear view)
        val answers_layout = findViewById<View>(R.id.answers_layout) as LinearLayout

        //Create EditText
        val et = EditText(this)
        //Set params
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        et.layoutParams = lp
        answers_layout.addView(et)
        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //skip
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //skip
            }

            override fun afterTextChanged(editable: Editable) {
                mAnswer = editable.toString().toLowerCase()
            }
        })
    }

    private fun showCheckBoxAnswers(qnum: Int) {
        //Setting answers_layout (linear view)
        val answers_layout = findViewById<View>(R.id.answers_layout) as LinearLayout
        //Reset checkbox click counts
        cbClicks = 0
        //Create CheckBoxes in array list
        for (i in 0..2) {
            //Setting checkbox's params
            cbl[i] = CheckBox(this)
            cbl[i]!!.text = mQuestions.getChoice(qnum)[i]
            cbl[i]!!.setTextColor(Color.BLACK)
            cbl[i]!!.setPadding(8, 16, 8, 16)
            cbl[i]!!.textSize = 24f
            cbl[i]!!.id = i

            //Add checkbox listener
            cbl[i]!!.setOnClickListener { view ->
                //If click any checkbox, addto the cbClicks count
                if ((view as CheckBox).isChecked) {
                    //Increment by 1
                    cbClicks++
                    //Display toast message
                    displayToastCheckBoxClick(cbClicks, QuestionNum)
                } else {
                    //If uncheck, decrease by 1
                    cbClicks--
                    displayToastCheckBoxClick(cbClicks, QuestionNum)
                }
            }

            //Add to answer_layout view
            answers_layout.addView(cbl[i])
        }
    }

    private fun showRadioButtonAnswers(qnum: Int) {
        //Setting answers_layout (linear view)
        val answers_layout = findViewById<View>(R.id.answers_layout) as LinearLayout
        //Create RadioGroup
        val rg = RadioGroup(this)
        rg.orientation = RadioGroup.VERTICAL
        //Set params
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        rg.layoutParams = lp

        //Create Radio Buttons in array list
        val rbl = arrayOfNulls<RadioButton>(3)
        for (i in 0..2) {
            //Setting radio button params
            rbl[i] = RadioButton(this)
            rbl[i]!!.text = mQuestions.getChoice(qnum)[i]
            rbl[i]!!.setTextColor(Color.BLACK)
            rbl[i]!!.setPadding(8, 16, 8, 16)
            rbl[i]!!.textSize = 24f
            rbl[i]!!.id = i
            //Add to radio group
            rg.addView(rbl[i])
        }

        //Create button listener
        rg.setOnCheckedChangeListener { radioGroup, ckId -> //Store the answer to mAnswer var when click
            mAnswer = mQuestions.getChoice(QuestionNum)[ckId]
        }
        //Add to answers_layout view
        answers_layout.addView(rg)
    }

    private fun validateName() {
        val extras = intent.extras
        if (extras != null) {
            val i = intent
            val iName = i.getStringExtra("mName")
            mNameView = findViewById<View>(R.id.name_textview) as TextView
            mNameView!!.text = String.format("Hi %s", iName.toUpperCase())
        } else {
            val intent_form = Intent(
                this@MainActivity,
                FormActivity::class.java
            )
            startActivity(intent_form)
        }
    }
}
