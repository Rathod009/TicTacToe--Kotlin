package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var board: Array<Array<Button>>
    var player:Boolean = true
    var turnCount:Int = 0
    var boardStatus = Array(3){IntArray(3)}
    var win = -1;

    private fun initializeBoardStatus() {
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                boardStatus[i][j] = -1
                board[i][j].setText("")
                board[i][j].isEnabled = true
            }
        }
    }

    private fun updateValue(row: Int, column: Int, player: Boolean) {
        var text = if(player) "X" else "0"

        board[row][column].apply {
            setText(text)
            isEnabled = false
        }

        boardStatus[row][column] = if(player) 1 else 0
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(btn1, btn2, btn3),
            arrayOf(btn4, btn5, btn6),
            arrayOf(btn7, btn8, btn9)
        )

        for (i in board) {
            for (btn in i)
                btn.setOnClickListener(this)
        }

        initializeBoardStatus()

        btnRestart.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn1 -> {
                updateValue(0,0,player)
            }
            R.id.btn2 -> {
                updateValue(0,1,player)
            }
            R.id.btn3 -> {
                updateValue(0,2,player)
            }
            R.id.btn4 -> {
                updateValue(1,0,player)
            }
            R.id.btn5 -> {
                updateValue(1,1,player)
            }
            R.id.btn6 -> {
                updateValue(1,2,player)
            }
            R.id.btn7 -> {
                updateValue(2,0,player)
            }
            R.id.btn8 -> {
                updateValue(2,1,player)
            }
            R.id.btn9 -> {
                updateValue(2,2,player)
            }
            R.id.btnRestart -> {
                restart();
            }
        }
        turnCount++

        if(v.id !== R.id.btnRestart)
            player = !player


        if(turnCount == 9)
            txtPlayer.setText("GAME DRAW");
        else
            txtPlayer.setText(if(player) "Player X"  else "Player Y")

        if(turnCount >= 5) {
            var win = checkWinner()
            if(win != -1)
            {
                txtPlayer.setText(if(win == 1) "Player X is WINNER" else "Player Y is WINNER")
                disable()

            }
        }


    }

    private fun disable() {

        for(i in board)
            for(btn in i)
            {
                btn.isEnabled = false
            }

    }

    private fun checkWinner(): Int {
        win = -1
        for(i in 0..2)
        {
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][1] == boardStatus[i][2])
                win = boardStatus[i][0]

            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[1][i] == boardStatus[2][i])
                win = boardStatus[0][i]

            if(win != -1)
                return win
        }

        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][2])
        {
            win = boardStatus[0][0]
        }
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][0])
        {
            win = boardStatus[0][2]
        }

        return win;
    }

    private fun restart() {
        player = true;
        initializeBoardStatus();
        turnCount = 0;

    }


}


