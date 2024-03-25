package com.example.tic_tac_toe_game

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.tic_tac_toe_game.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var player = "p1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btn1.setOnClickListener { buttonClick(btn1) }
            btn2.setOnClickListener { buttonClick(btn2) }
            btn3.setOnClickListener { buttonClick(btn3) }
            btn4.setOnClickListener { buttonClick(btn4) }
            btn5.setOnClickListener { buttonClick(btn5) }
            btn6.setOnClickListener { buttonClick(btn6) }
            btn7.setOnClickListener { buttonClick(btn7) }
            btn8.setOnClickListener { buttonClick(btn8) }
            btn9.setOnClickListener { buttonClick(btn9) }
            btnReset.setOnClickListener { reset() }
        }
    }

    private fun buttonClick(btn: Button) {
        if (btn.text == "") {
            if (player == "p1") {
                player = "p2"
                btn.text = "X"
                if(!isBoardFull()) binding.txtPlayerMove.text = "Player 2's move - O"
            } else {
                player = "p1"
                btn.text = "O"
                if(!isBoardFull()) binding.txtPlayerMove.text = "Player 1's move - X"
            }
        }
        win()
    }

    private fun win() {
        with(binding) {
            // Check for horizontal wins
            if (btn1.text == btn2.text && btn2.text == btn3.text && btn1.text != "") {
                highlightWinningButtons(btn1, btn2, btn3)
                showWinner(btn1.text.toString())
            } else if (btn4.text == btn5.text && btn5.text == btn6.text && btn4.text != "") {
                highlightWinningButtons(btn4, btn5, btn6)
                showWinner(btn4.text.toString())
            } else if (btn7.text == btn8.text && btn8.text == btn9.text && btn7.text != "") {
                highlightWinningButtons(btn7, btn8, btn9)
                showWinner(btn7.text.toString())
            }

            // Check for vertical wins
            else if (btn1.text == btn4.text && btn4.text == btn7.text && btn1.text != "") {
                highlightWinningButtons(btn1, btn4, btn7)
                showWinner(btn1.text.toString())
            } else if (btn2.text == btn5.text && btn5.text == btn8.text && btn2.text != "") {
                highlightWinningButtons(btn2, btn5, btn8)
                showWinner(btn2.text.toString())
            } else if (btn3.text == btn6.text && btn6.text == btn9.text && btn3.text != "") {
                highlightWinningButtons(btn3, btn6, btn9)
                showWinner(btn3.text.toString())
            }

            // Check for diagonal wins
            else if (btn1.text == btn5.text && btn5.text == btn9.text && btn1.text != "") {
                highlightWinningButtons(btn1, btn5, btn9)
                showWinner(btn1.text.toString())
            } else if (btn3.text == btn5.text && btn5.text == btn7.text && btn3.text != "") {
                highlightWinningButtons(btn3, btn5, btn7)
                showWinner(btn3.text.toString())
            }

            // Check for tie game
            else if (isBoardFull()) {
                toast("Tie Game")
                //txtResult.text = "Tie Game"
                disableButton()
            }
        }
    }

    private fun highlightWinningButtons(vararg buttons: Button) {
        val winColor = Color.YELLOW
        buttons.forEach { button ->
            button.setBackgroundColor(winColor)
            button.setTextColor(Color.RED)
        }
        binding.txtPlayerMove.text = ""
    }

    private fun showWinner(winner: String) {
        toast("$winner won the Game")
        //binding.txtResult.text = "$winner won the Game"
        disableButton()
    }

    private fun isBoardFull(): Boolean {
        val isFull = binding.btn1.text.isNotEmpty() && binding.btn2.text.isNotEmpty() &&
                binding.btn3.text.isNotEmpty() && binding.btn4.text.isNotEmpty() &&
                binding.btn5.text.isNotEmpty() && binding.btn6.text.isNotEmpty() &&
                binding.btn7.text.isNotEmpty() && binding.btn8.text.isNotEmpty() &&
                binding.btn9.text.isNotEmpty()

        if (isFull) binding.txtPlayerMove.text = ""

        return isFull
    }

    private fun toast(msg: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast_layout, findViewById(R.id.customToast))
        val toastText = layout.findViewById<TextView>(R.id.customToast)
        toastText.text = msg

        val toast = Toast(applicationContext)
        toast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM, 0, 250)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()
    }

    private fun reset() {
        with(binding) {
            btn1.text = ""
            btn2.text = ""
            btn3.text = ""
            btn4.text = ""
            btn5.text = ""
            btn6.text = ""
            btn7.text = ""
            btn8.text = ""
            btn9.text = ""
            binding.txtPlayerMove.text = ""
            player = "p1"
            //txtResult.text = ""
            enableButton()
            resetBtnBg()
            resetBtnTxtColor()
        }
    }

    private fun resetBtnTxtColor() {
        with(binding) {
            val textColor = ContextCompat.getColor(this@MainActivity, R.color.btnTxtColor)
            btn1.setTextColor(textColor)
            btn2.setTextColor(textColor)
            btn3.setTextColor(textColor)
            btn4.setTextColor(textColor)
            btn5.setTextColor(textColor)
            btn6.setTextColor(textColor)
            btn7.setTextColor(textColor)
            btn8.setTextColor(textColor)
            btn9.setTextColor(textColor)
        }
    }

    private fun resetBtnBg() {
        with(binding) {
            val backgroundColor = ContextCompat.getColor(this@MainActivity, R.color.btnTint)
            btn1.setBackgroundColor(backgroundColor)
            btn2.setBackgroundColor(backgroundColor)
            btn3.setBackgroundColor(backgroundColor)
            btn4.setBackgroundColor(backgroundColor)
            btn5.setBackgroundColor(backgroundColor)
            btn6.setBackgroundColor(backgroundColor)
            btn7.setBackgroundColor(backgroundColor)
            btn8.setBackgroundColor(backgroundColor)
            btn9.setBackgroundColor(backgroundColor)
        }
    }

    private fun disableButton() {
        with(binding) {
            btn1.isEnabled = false
            btn2.isEnabled = false
            btn3.isEnabled = false
            btn4.isEnabled = false
            btn5.isEnabled = false
            btn6.isEnabled = false
            btn7.isEnabled = false
            btn8.isEnabled = false
            btn9.isEnabled = false
        }
    }

    private fun enableButton() {
        with(binding) {
            btn1.isEnabled = true
            btn2.isEnabled = true
            btn3.isEnabled = true
            btn4.isEnabled = true
            btn5.isEnabled = true
            btn6.isEnabled = true
            btn7.isEnabled = true
            btn8.isEnabled = true
            btn9.isEnabled = true
        }
    }
}