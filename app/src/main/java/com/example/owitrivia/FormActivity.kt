package com.example.owitrivia

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        //Prevent keyboard from popping up when starting
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    //Start Quiz Button
    fun startBtn(view: View?) {
        val mNameView = findViewById<View>(R.id.name_edittext) as EditText
        val mName = mNameView.text.toString().trim { it <= ' ' }
        if (mName == "") {
            //If empty string, make a toast message
            val enter_name = getString(R.string.txt_pls_enter_ur_name)
            Toast.makeText(this@FormActivity,
                enter_name,
                Toast.LENGTH_SHORT).show()
        } else {
            //Go to quiz
            goToQuiz(mName)
        }
    }

    //Go to Quiz Intent
    private fun goToQuiz(mname: String) {
        val intent_main = Intent(this@FormActivity, MainActivity::class.java)
        intent_main.putExtra("mName", mname)
        startActivity(intent_main)
    }

    //Prevent Back Button
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //do nothing
        }
        return false
    }
}