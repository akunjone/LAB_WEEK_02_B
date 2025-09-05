package com.example.lab_week_02_b

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    companion object{
        private const val COLOR_KEY = "COLOR_KEY"
        const val ERROR_KEY = "ERROR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.background_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(intent != null){
            val colorCode = intent.getStringExtra(COLOR_KEY)

            val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)
            try{
                backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))
            }
            catch(ex: IllegalArgumentException){
                Intent().let{
                    errorIntent ->
                    errorIntent.putExtra(ERROR_KEY, true)
                    setResult(Activity.RESULT_OK, errorIntent)
                    finish()
                }
            }

            val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
            resultMessage.text = getString(R.string.color_code_result_message, colorCode?.uppercase())
        }

        val back_button = findViewById<Button>(R.id.button_back)
        back_button.setOnClickListener {
            finish() //biar langsung back krn udah kelar
        }
    }
}