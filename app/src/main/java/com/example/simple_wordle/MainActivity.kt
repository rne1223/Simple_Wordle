package com.example.simple_wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private var wordToGuess = FourLetterWordList.getRandomFourLetterWord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textInput = findViewById<TextInputEditText>(R.id.textInputEditText)
        val leftSide = findViewById<TextView>(R.id.leftSide)
        val rightSide = findViewById<TextView>(R.id.rightSide)
        val button = findViewById<Button>(R.id.button)
        val resetBtn = findViewById<Button>(R.id.resetBtn)
        val gameOverTxt = findViewById<TextView>(R.id.gameOverText)


        var guess = 1
        button.setOnClickListener {

            var userGuess = textInput.text.toString().uppercase()
            Log.v("WORDLE", userGuess)
            Log.v("WORDLE", userGuess.length.toString())

            // Check for user input
            if (userGuess.length  != 4 ) {
                Toast.makeText(it.context, "Please enter a 4 letter word", Toast.LENGTH_SHORT) .show()
                return@setOnClickListener
            }

            // Check guesses
            if (guess >= 4) {
                resetBtn.visibility = View.VISIBLE
                gameOverTxt.text = wordToGuess
                gameOverTxt.visibility = View.VISIBLE


                textInput.visibility = View.GONE
                leftSide.visibility = View.GONE
                rightSide.visibility = View.GONE
                button.visibility = View.GONE
            }


            // Display Information
            leftSide.text = leftSide.text.toString()  + "\n Guess #" + guess.toString()
            rightSide.text = rightSide.text.toString() + "\n" +  userGuess

            leftSide.text = leftSide.text.toString()  + "\n Guess #" + guess.toString() + " Check"
            rightSide.text = rightSide.text.toString() + "\n" + checkGuess(userGuess)


            leftSide.text = leftSide.text.toString()  + "\n"
            rightSide.text = rightSide.text.toString() + "\n"
            guess++
        }

        // Reset the Game
        resetBtn.setOnClickListener {

            wordToGuess = FourLetterWordList.getRandomFourLetterWord()
            guess = 1
            textInput.visibility = View.VISIBLE
            button.visibility = View.VISIBLE
            leftSide.visibility = View.VISIBLE
            rightSide.visibility = View.VISIBLE

            resetBtn.visibility = View.GONE
            gameOverTxt.visibility = View.GONE

            leftSide.text = ""
            rightSide.text = ""
        }
    }




    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
       var result = ""

        Log.v("WORDLE", wordToGuess)
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}