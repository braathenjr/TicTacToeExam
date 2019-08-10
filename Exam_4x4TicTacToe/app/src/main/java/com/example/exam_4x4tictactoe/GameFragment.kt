package com.example.exam_4x4tictactoe

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import kotlinx.android.synthetic.main.fragment_game.*
import java.util.ArrayList

private const val TAG = "GameFragment"


private const val Player1Arg = "Player 1"
private const val Player2Arg = "Player 2"

class GameFragment : Fragment() {
    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()
    private var activePlayer = 1
    private var player1Name: Player? = null
    private var player2Name: Player? = null
    private var playAI: Boolean = false
    private var dataPasser: OnDataPass? = null


    private lateinit var timer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate starts")
        super.onCreate(savedInstanceState)
        player1Name = arguments?.getSerializable(Player1Arg) as Player?

        player2Name = arguments?.getSerializable(Player2Arg) as Player?

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_game, container, false)

        timer = view.findViewById(R.id.timerView)
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    fun checkWinner() {
        var winner = 0

        //top left to bottom right
        if(player1.contains(1) && player1.contains(6) && player1.contains(11) && player1.contains(16)){
            winner = 1
        }
        else if(player2.contains(1) && player2.contains(6) && player2.contains(11) && player2.contains(16)){
            winner = 2
        }

        //top right to bottom left
        else if(player1.contains(4) && player1.contains(7) && player1.contains(10) && player1.contains(13)){
            winner = 1
        }
        else if(player2.contains(4) && player2.contains(7) && player2.contains(10) && player2.contains(13)){
            winner = 2
        }

        //row 1
        else if(player1.contains(1) && player1.contains(2) && player1.contains(3) && player1.contains(4)){
            winner = 1
        }
        else if(player2.contains(1) && player2.contains(2) && player2.contains(3) && player2.contains(4)){
            winner = 2
        }

        //row 2
        else if(player1.contains(5) && player1.contains(6) && player1.contains(7) && player1.contains(8)){
            winner = 1
        }
        else if(player2.contains(5) && player2.contains(6) && player2.contains(7) && player2.contains(8)){
            winner = 2
        }

        //row 3
        else if(player1.contains(9) && player1.contains(10) && player1.contains(11) && player1.contains(12)){
            winner = 1
        }
        else if(player2.contains(9) && player2.contains(10) && player2.contains(11) && player2.contains(12)){
            winner = 2
        }

        //row 4
        else if(player1.contains(13) && player1.contains(14) && player1.contains(15) && player1.contains(16)){
            winner = 1
        }
        else if(player2.contains(13) && player2.contains(14) && player2.contains(15) && player2.contains(16)){
            winner = 2
        }

        //col 1
        else if(player1.contains(1) && player1.contains(5) && player1.contains(9) && player1.contains(13)){
            winner = 1
        }
        else if(player2.contains(1) && player2.contains(5) && player2.contains(9) && player2.contains(31)){
            winner = 2
        }

        //col 2
        else if(player1.contains(2) && player1.contains(6) && player1.contains(10) && player1.contains(14)){
            winner = 1
        }
        else if(player2.contains(2) && player2.contains(6) && player2.contains(10) && player2.contains(14)){
            winner = 2
        }

        //col 3
        else if(player1.contains(3) && player1.contains(7) && player1.contains(11) && player1.contains(15)){
            winner = 1
        }
        else if(player2.contains(3) && player2.contains(7) && player2.contains(11) && player2.contains(15)){
            winner = 2
        }

        //col 4
        else if(player1.contains(4) && player1.contains(8) && player1.contains(12) && player1.contains(16)){
            winner = 1
        }
        else if(player2.contains(4) && player2.contains(8) && player2.contains(12) && player2.contains(16)){
            winner = 2
        }
        else if(player1.size == 8 && player2.size == 8){
            player.text = "out of options"
            timer.stop()
            disableBtn()
        }


            if(winner == 1){

                player.text = "${player1Name!!.name} wins"
                //Toast.makeText(this, "Player 1 won!",Toast.LENGTH_SHORT).show()
                player1Name!!.wins++
                dataPasser!!.onPassData(player1Name!!)
                timer.stop()
                disableBtn()

            }else if (winner == 2){
                player.text = "${player2Name!!.name} wins"
                // Toast.makeText(this, "Player 2 won!",Toast.LENGTH_LONG).show()
                player2Name!!.wins++
                dataPasser!!.onPassData(player2Name!!)
                timer.stop()
                disableBtn()
            }

    }

    fun disableBtn(){
        btn1.isEnabled = false
        btn2.isEnabled = false
        btn3.isEnabled = false
        btn4.isEnabled = false
        btn5.isEnabled = false
        btn6.isEnabled = false
        btn7.isEnabled = false
        btn8.isEnabled = false
        btn9.isEnabled = false
        btn10.isEnabled = false
        btn11.isEnabled = false
        btn12.isEnabled = false
        btn13.isEnabled = false
        btn14.isEnabled = false
        btn15.isEnabled = false
        btn16.isEnabled = false
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        player.text = player1Name!!.name


        timer.start()

        btn1.setOnClickListener {
            if(activePlayer == 1){
                btn1.text = "X"; activePlayer = 2; player1.add(1); player.text = player2Name!!.name
            } else if(activePlayer == 2) {
                btn1.text = "O"; activePlayer = 1; player2.add(1); player.text = player1Name!!.name
            }
            btn1.isClickable = false

            checkWinner()
        }

        btn2.setOnClickListener {
            if(activePlayer == 1){
                btn2.text = "X"; activePlayer = 2; player1.add(2); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn2.text = "O"; activePlayer = 1; player2.add(2); player.text = player1Name!!.name
            }
            btn2.isClickable = false

            checkWinner()
        }

        btn3.setOnClickListener {
            if(activePlayer == 1){
                btn3.text = "X"; activePlayer = 2; player1.add(3); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn3.text = "O"; activePlayer = 1; player2.add(3); player.text = player1Name!!.name
            }
            btn3.isClickable = false
            checkWinner()
        }

        btn4.setOnClickListener {
            if(activePlayer == 1){
                btn4.text = "X"; activePlayer = 2; player1.add(4); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn4.text = "O"; activePlayer = 1; player2.add(4); player.text = player1Name!!.name
            }
            btn4.isClickable = false
            checkWinner()
        }

        btn5.setOnClickListener {
            if(activePlayer == 1){
                btn5.text = "X"; activePlayer = 2; player1.add(5); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn5.text = "O"; activePlayer = 1; player2.add(5); player.text = player1Name!!.name
            }
            btn5.isClickable = false
            checkWinner()
        }

        btn6.setOnClickListener {
            if(activePlayer == 1){
                btn6.text = "X"; activePlayer = 2; player1.add(6); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn6.text = "O"; activePlayer = 1; player2.add(6); player.text = player1Name!!.name
            }
            btn6.isClickable = false
            checkWinner()
        }

        btn7.setOnClickListener {
            if(activePlayer == 1){
                btn7.text = "X"; activePlayer = 2; player1.add(7); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn7.text = "O"; activePlayer = 1; player2.add(7); player.text = player1Name!!.name
            }
            btn7.isClickable = false
            checkWinner()
        }

        btn8.setOnClickListener {
            if(activePlayer == 1){
                btn8.text = "X"; activePlayer = 2; player1.add(8); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn8.text = "O"; activePlayer = 1; player2.add(8); player.text = player1Name!!.name
            }
            btn8.isClickable = false
            checkWinner()
        }

        btn9.setOnClickListener {
            if(activePlayer == 1){
                btn9.text = "X"; activePlayer = 2; player1.add(9); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn9.text = "O"; activePlayer = 1; player2.add(9); player.text = player1Name!!.name
            }
            btn9.isClickable = false
            checkWinner()
        }

        btn10.setOnClickListener {
            if(activePlayer == 1){
                btn10.text = "X"; activePlayer = 2; player1.add(10); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn10.text = "O"; activePlayer = 1; player2.add(10); player.text = player1Name!!.name
            }
            btn10.isClickable = false
            checkWinner()
        }

        btn11.setOnClickListener {
            if(activePlayer == 1){
                btn11.text = "X"; activePlayer = 2; player1.add(11); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn11.text = "O"; activePlayer = 1; player2.add(11); player.text = player1Name!!.name
            }
            btn11.isClickable = false
            checkWinner()
        }
        btn12.setOnClickListener {
            if (activePlayer == 1) {
                btn12.text = "X"; activePlayer = 2; player1.add(12); player.text = player2Name!!.name
            } else if (activePlayer == 2) {
                btn12.text = "O"; activePlayer = 1; player2.add(12); player.text = player1Name!!.name
            }
            btn12.isClickable = false
            checkWinner()
        }

        btn13.setOnClickListener {
            if(activePlayer == 1){
                btn13.text = "X"; activePlayer = 2; player1.add(13); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn13.text = "O"; activePlayer = 1; player2.add(13); player.text = player1Name!!.name
            }
            btn13.isClickable = false
            checkWinner()
        }

        btn14.setOnClickListener {
            if(activePlayer == 1){
                btn14.text = "X"; activePlayer = 2; player1.add(14); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn14.text = "O"; activePlayer = 1; player2.add(14); player.text = player1Name!!.name
            }
            btn14.isClickable = false
            checkWinner()
        }

        btn15.setOnClickListener {
            if(activePlayer == 1){
                btn15.text = "X"; activePlayer = 2; player1.add(15); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn15.text = "O"; activePlayer = 1; player2.add(15); player.text = player1Name!!.name
            }
            btn15.isClickable = false
            checkWinner()
        }

        btn16.setOnClickListener {
            if(activePlayer == 1){
                btn16.text = "X"; activePlayer = 2; player1.add(16); player.text = player2Name!!.name
            } else if(activePlayer == 2){
                btn16.text = "O"; activePlayer = 1; player2.add(16); player.text = player1Name!!.name
            }
            btn16.isClickable = false
            checkWinner()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnDataPass) {
            dataPasser = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnDataPass")
        }

    }

    override fun onDetach() {
        super.onDetach()
        dataPasser = null

    }
    override fun onStop() {
        timer.stop()
        super.onStop()
    }

    override fun onPause() {
        timer.stop()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        timer.start()
    }

    interface OnDataPass {

        fun onPassData(player: Player)


    }


    companion object {

        @JvmStatic
        fun newInstance(param1: Player, param2: Player) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(Player1Arg, param1)
                    putSerializable(Player2Arg, param2)
                }
            }
    }
}
