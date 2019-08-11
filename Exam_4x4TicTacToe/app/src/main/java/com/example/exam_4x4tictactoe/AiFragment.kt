package com.example.exam_4x4tictactoe

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import kotlinx.android.synthetic.main.fragment_ai.*
import java.util.ArrayList
import java.util.Random

private const val TAG = "AiFragment"


private const val Player1Arg = "Player 1"
private const val Player2Arg = "Player 2"

class AiFragment : Fragment() {

    private var player1Name: Player? = null
    private var playerAI: Player? = null
    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()
    private var activePlayer = 1
    private var dataPasser: OnDataPass? = null


    private lateinit var timer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate starts")
        super.onCreate(savedInstanceState)
        player1Name = arguments?.getSerializable(Player1Arg) as Player?

        playerAI = arguments?.getSerializable(Player2Arg) as Player?

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_ai, container, false)

        timer = view.findViewById(R.id.timerView)

        return inflater.inflate(R.layout.fragment_ai, container, false)
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
            player.text = "${playerAI?.name} wins"
            // Toast.makeText(this, "Player 2 won!",Toast.LENGTH_LONG).show()
            playerAI!!.wins++
            dataPasser!!.onPassData(playerAI!!)
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
    /*
    fun AiMove(){
        //check if Player 1 got 3 in a row
        //left row top to bottom
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            btn13.text = "O"
            player2.add(13)
            activePlayer = 1

            player.text = player1Name!!.name
            btn13.isClickable = false

        }
        if(player1.contains(1) && player1.contains(5) && player1.contains(13)){
            btn9.text = "O"
            player2.add(9)
            activePlayer = 1

            player.text = player1Name!!.name
            btn9.isClickable = false
        }
        if(player1.contains(1) && player1.contains(9) && player1.contains(13)){
            btn5.text = "O"
            player2.add(5)
            activePlayer = 1

            player.text = player1Name!!.name
            btn5.isClickable = false
        }
        if(player1.contains(5) && player1.contains(9) && player1.contains(13)){
            btn1.text = "O"
            player2.add(1)
            activePlayer = 1

            player.text = player1Name!!.name
            btn1.isClickable = false
        }
        //second to the left row top to bottom
        if(player1.contains(2) && player1.contains(6) && player1.contains(10)){
            btn14.text = "O"
            player2.add(14)
            activePlayer = 1

            player.text = player1Name!!.name
            btn14.isClickable = false
        }
        if(player1.contains(2) && player1.contains(6) && player1.contains(14)){
            btn10.text = "O"
            player2.add(10)
            activePlayer = 1

            player.text = player1Name!!.name
            btn10.isClickable = false
        }
        if(player1.contains(2) && player1.contains(10) && player1.contains(14)){
            btn6.text = "O"
            player2.add(6)
            activePlayer = 1

            player.text = player1Name!!.name
            btn6.isClickable = false
        }
        if(player1.contains(6) && player1.contains(6) && player1.contains(14)){
            btn2.text = "O"
            player2.add(10)
            activePlayer = 1

            player.text = player1Name!!.name
            btn2.isClickable = false
        }
        //second to the right row top to bottom
        if(player1.contains(3) && player1.contains(7) && player1.contains(11)){
            btn15.text = "O"
            player2.add(15)
            activePlayer = 1

            player.text = player1Name!!.name
            btn15.isClickable = false
        }
        if(player1.contains(3) && player1.contains(7) && player1.contains(15)){
            btn11.text = "O"
            player2.add(11)
            activePlayer = 1

            player.text = player1Name!!.name
            btn11.isClickable = false
        }
        if(player1.contains(3) && player1.contains(11) && player1.contains(15)){
            btn7.text = "O"
            player2.add(7)
            activePlayer = 1

            player.text = player1Name!!.name
            btn7.isClickable = false
        }
        if(player1.contains(7) && player1.contains(11) && player1.contains(15)){
            btn3.text = "O"
            player2.add(3)
            activePlayer = 1

            player.text = player1Name!!.name
            btn3.isClickable = false
        }
        // right row top to bottom
        if(player1.contains(4) && player1.contains(8) && player1.contains(12)){
            btn12.text = "O"
            player2.add(12)
            activePlayer = 1

            player.text = player1Name!!.name
            btn12.isClickable = false
        }
        if(player1.contains(4) && player1.contains(8) && player1.contains(16)){
            btn12.text = "O"
            player2.add(12)
            activePlayer = 1

            player.text = player1Name!!.name
            btn12.isClickable = false
        }
        if(player1.contains(4) && player1.contains(12) && player1.contains(16)){
            btn8.text = "O"
            player2.add(8)
            activePlayer = 1

            player.text = player1Name!!.name
            btn8.isClickable = false
        }
        if(player1.contains(8) && player1.contains(12) && player1.contains(16)){
            btn16.text = "O"
            player2.add(16)
            activePlayer = 1

            player.text = player1Name!!.name
            btn16.isClickable = false
        }
        //top row left to right
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            btn4.text = "O"
            player2.add(4)
            activePlayer = 1

            player.text = player1Name!!.name
            btn4.isClickable = false
        }
        if(player1.contains(1) && player1.contains(2) && player1.contains(4)){
            btn3.text = "O"
            player2.add(3)
            activePlayer = 1

            player.text = player1Name!!.name
            btn3.isClickable = false
        }
        if(player1.contains(1) && player1.contains(3) && player1.contains(4)){
            btn2.text = "O"
            player2.add(2)
            activePlayer = 1

            player.text = player1Name!!.name
            btn2.isClickable = false
        }
        if(player1.contains(2) && player1.contains(3) && player1.contains(4)){
            btn1.text = "O"
            player2.add(1)
            activePlayer = 1

            player.text = player1Name!!.name
            btn1.isClickable = false
        }
        //second row left to right
        if(player1.contains(5) && player1.contains(6) && player1.contains(7)){
            btn8.text = "O"
            player2.add(8)
            activePlayer = 1

            player.text = player1Name!!.name
            btn8.isClickable = false
        }
        if(player1.contains(5) && player1.contains(6) && player1.contains(8)){
            btn7.text = "O"
            player2.add(7)
            activePlayer = 1

            player.text = player1Name!!.name
            btn7.isClickable = false
        }
        if(player1.contains(5) && player1.contains(7) && player1.contains(8)){
            btn6.text = "O"
            player2.add(6)
            activePlayer = 1

            player.text = player1Name!!.name
            btn6.isClickable = false
        }
        if(player1.contains(6) && player1.contains(7) && player1.contains(8)){
            btn5.text = "O"
            player2.add(5)
            activePlayer = 1

            player.text = player1Name!!.name
            btn5.isClickable = false
        }
        //third row left to right
        if(player1.contains(9) && player1.contains(10) && player1.contains(11)){
            btn12.text = "O"
            player2.add(12)
            activePlayer = 1

            player.text = player1Name!!.name
            btn12.isClickable = false
        }
        if(player1.contains(9) && player1.contains(10) && player1.contains(12)){
            btn11.text = "O"
            player2.add(11)
            activePlayer = 1

            player.text = player1Name!!.name
            btn11.isClickable = false
        }
        if(player1.contains(9) && player1.contains(11) && player1.contains(12)){
            btn10.text = "O"
            player2.add(10)
            activePlayer = 1

            player.text = player1Name!!.name
            btn10.isClickable = false
        }
        if(player1.contains(10) && player1.contains(1) && player1.contains(12)){
            btn9.text = "O"
            player2.add(9)
            activePlayer = 1

            player.text = player1Name!!.name
            btn9.isClickable = false
        }
        //fourth row left to right
        if(player1.contains(13) && player1.contains(14) && player1.contains(15)){
            btn16.text = "O"
            player2.add(16)
            activePlayer = 1

            player.text = player1Name!!.name
            btn16.isClickable = false
        }
        if(player1.contains(13) && player1.contains(14) && player1.contains(16)){
            btn15.text = "O"
            player2.add(15)
            activePlayer = 1

            player.text = player1Name!!.name
            btn15.isClickable = false
        }
        if(player1.contains(13) && player1.contains(15) && player1.contains(16)){
            btn14.text = "O"
            player2.add(14)
            activePlayer = 1

            player.text = player1Name!!.name
            btn14.isClickable = false
        }
        if(player1.contains(14) && player1.contains(15) && player1.contains(16)){
            btn13.text = "O"
            player2.add(13)
            activePlayer = 1

            player.text = player1Name!!.name
            btn13.isClickable = false
        }
        //digonal top left to bottom right
        if(player1.contains(1) && player1.contains(6) && player1.contains(11)){
            btn16.text = "O"
            player2.add(16)
            activePlayer = 1

            player.text = player1Name!!.name
            btn16.isClickable = false
        }
        if(player1.contains(1) && player1.contains(6) && player1.contains(16)){
            btn11.text = "O"
            player2.add(11)
            activePlayer = 1

            player.text = player1Name!!.name
            btn11.isClickable = false
        }
        if(player1.contains(1) && player1.contains(11) && player1.contains(16)){
            btn6.text = "O"
            player2.add(6)
            activePlayer = 1

            player.text = player1Name!!.name
            btn6.isClickable = false
        }
        if(player1.contains(6) && player1.contains(11) && player1.contains(16)){
            btn1.text = "O"
            player2.add(1)
            activePlayer = 1

            player.text = player1Name!!.name
            btn1.isClickable = false
        }
        //diagonal bottom left to top right
        if(player1.contains(13) && player1.contains(10) && player1.contains(7)){
            btn4.text = "O"
            player2.add(4)
            activePlayer = 1

            player.text = player1Name!!.name
            btn4.isClickable = false
        }
        if(player1.contains(13) && player1.contains(10) && player1.contains(4)){
            btn7.text = "O"
            player2.add(7)
            activePlayer = 1

            player.text = player1Name!!.name
            btn7.isClickable = false
        }
        if(player1.contains(13) && player1.contains(7) && player1.contains(4)){
            btn10.text = "O"
            player2.add(10)
            activePlayer = 1

            player.text = player1Name!!.name
            btn10.isClickable = false
        }
        if(player1.contains(10) && player1.contains(7) && player1.contains(4) && !player2.contains(13)){
            btn13.text = "O"
            player2.add(13)
            activePlayer = 1

            player.text = player1Name!!.name
            btn13.isClickable = false
        }else{
            AiMove()
        }


        checkWinner()
    }*/
    fun AiMove(){
        
        // pick direct opposite of opponent
        if(player1.contains(1) && !player2.contains(16) && !player1.contains(16)){
            btn16.text = "O"
            player2.add(16)
            activePlayer = 1

            player.text = player1Name!!.name
            btn16.isClickable = false
        }else if(player1.contains(2) && !player2.contains(14) && !player1.contains(14)){
            btn14.text = "O"
            player2.add(14)
            activePlayer = 1

            player.text = player1Name!!.name
            btn14.isClickable = false
        }else if(player1.contains(3) && !player2.contains(15) && !player1.contains(15)){
            btn15.text = "O"
            player2.add(15)
            activePlayer = 1

            player.text = player1Name!!.name
            btn15.isClickable = false
        }else if(player1.contains(4) && !player2.contains(13) && !player1.contains(13)){
            btn13.text = "O"
            player2.add(13)
            activePlayer = 1

            player.text = player1Name!!.name
            btn13.isClickable = false
        }else if(player1.contains(5) && !player2.contains(8) && !player1.contains(8)){
            btn8.text = "O"
            player2.add(8)
            activePlayer = 1

            player.text = player1Name!!.name
            btn8.isClickable = false
        }else if(player1.contains(6) && !player2.contains(7) && !player1.contains(7)){
            btn7.text = "O"
            player2.add(7)
            activePlayer = 1

            player.text = player1Name!!.name
            btn7.isClickable = false
        }else if(player1.contains(7) && !player2.contains(6) && !player1.contains(6)){
            btn6.text = "O"
            player2.add(6)
            activePlayer = 1

            player.text = player1Name!!.name
            btn6.isClickable = false
        }else if(player1.contains(8) && !player2.contains(5) && !player1.contains(5)){
            btn5.text = "O"
            player2.add(5)
            activePlayer = 1

            player.text = player1Name!!.name
            btn5.isClickable = false
        }else if(player1.contains(9) && !player2.contains(12) && !player1.contains(12)){
            btn12.text = "O"
            player2.add(12)
            activePlayer = 1

            player.text = player1Name!!.name
            btn12.isClickable = false
        }else if(player1.contains(10) && !player2.contains(11) && !player2.contains(11)){
            btn11.text = "O"
            player2.add(11)
            activePlayer = 1

            player.text = player1Name!!.name
            btn11.isClickable = false
        }else if(player1.contains(11) && !player2.contains(10) && !player1.contains(10)){
            btn10.text = "O"
            player2.add(10)
            activePlayer = 1

            player.text = player1Name!!.name
            btn10.isClickable = false
        }else if(player1.contains(12) && !player2.contains(9) && !player1.contains(9)){
            btn9.text = "O"
            player2.add(9)
            activePlayer = 1

            player.text = player1Name!!.name
            btn9.isClickable = false
        }else if(player1.contains(13) && !player2.contains(4) && !player1.contains(4)){
            btn4.text = "O"
            player2.add(4)
            activePlayer = 1

            player.text = player1Name!!.name
            btn4.isClickable = false
        }else if(player1.contains(14) && !player2.contains(2) && !player1.contains(2)){
            btn2.text = "O"
            player2.add(2)
            activePlayer = 1

            player.text = player1Name!!.name
            btn2.isClickable = false
        }else if(player1.contains(15) && !player2.contains(3) && !player1.contains(3)){
            btn9.text = "O"
            player2.add(9)
            activePlayer = 1

            player.text = player1Name!!.name
            btn9.isClickable = false
        }else if(player1.contains(16) && !player2.contains(1) && !player1.contains(1)){
            btn1.text = "O"
            player2.add(1)
            activePlayer = 1

            player.text = player1Name!!.name
            btn1.isClickable = false
        }


        checkWinner()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        player.text = player1Name!!.name


        timer.start()

        btn1.setOnClickListener {
            if(activePlayer == 1){
                btn1.text = "X"; activePlayer = 2; player1.add(1); player.text = playerAI!!.name; AiMove()
            }
            btn1.isClickable = false
            checkWinner()

        }

        btn2.setOnClickListener {
            if(activePlayer == 1){
                btn2.text = "X"; activePlayer = 2; player1.add(2); player.text = playerAI!!.name; AiMove()
            }
            btn2.isClickable = false

            checkWinner()
        }

        btn3.setOnClickListener {
            if(activePlayer == 1){
                btn3.text = "X"; activePlayer = 2; player1.add(3); player.text = playerAI!!.name; AiMove()
            }
            btn3.isClickable = false
            checkWinner()
        }

        btn4.setOnClickListener {
            if(activePlayer == 1){
                btn4.text = "X"; activePlayer = 2; player1.add(4); player.text = playerAI!!.name; AiMove()
            }
            btn4.isClickable = false
            checkWinner()
        }

        btn5.setOnClickListener {
            if(activePlayer == 1){
                btn5.text = "X"; activePlayer = 2; player1.add(5); player.text = playerAI!!.name; AiMove()
            }
            btn5.isClickable = false
            checkWinner()
        }

        btn6.setOnClickListener {
            if(activePlayer == 1){
                btn6.text = "X"; activePlayer = 2; player1.add(6); player.text = playerAI!!.name; AiMove()
            }
            btn6.isClickable = false
            checkWinner()
        }

        btn7.setOnClickListener {
            if(activePlayer == 1){
                btn7.text = "X"; activePlayer = 2; player1.add(7); player.text = playerAI!!.name; AiMove()
            }
            btn7.isClickable = false
            checkWinner()
        }

        btn8.setOnClickListener {
            if(activePlayer == 1){
                btn8.text = "X"; activePlayer = 2; player1.add(8); player.text = playerAI!!.name; AiMove()
            }
            btn8.isClickable = false
            checkWinner()
        }

        btn9.setOnClickListener {
            if(activePlayer == 1){
                btn9.text = "X"; activePlayer = 2; player1.add(9); player.text = playerAI!!.name; AiMove()
            }
            btn9.isClickable = false
            checkWinner()
        }

        btn10.setOnClickListener {
            if(activePlayer == 1){
                btn10.text = "X"; activePlayer = 2; player1.add(10); player.text = playerAI!!.name; AiMove()
            }
            btn10.isClickable = false
            checkWinner()
        }

        btn11.setOnClickListener {
            if(activePlayer == 1){
                btn11.text = "X"; activePlayer = 2; player1.add(11); player.text = playerAI!!.name; AiMove()
            }
            btn11.isClickable = false
            checkWinner()
        }
        btn12.setOnClickListener {
            if (activePlayer == 1) {
                btn12.text = "X"; activePlayer = 2; player1.add(12); player.text = playerAI!!.name; AiMove()
            }
            btn12.isClickable = false
            checkWinner()
        }

        btn13.setOnClickListener {
            if(activePlayer == 1){
                btn13.text = "X"; activePlayer = 2; player1.add(13); player.text = playerAI!!.name; AiMove()
            }
            btn13.isClickable = false
            checkWinner()
        }

        btn14.setOnClickListener {
            if(activePlayer == 1){
                btn14.text = "X"; activePlayer = 2; player1.add(14); player.text = playerAI!!.name; AiMove()
            }
            btn14.isClickable = false
            checkWinner()
        }

        btn15.setOnClickListener {
            if(activePlayer == 1){
                btn15.text = "X"; activePlayer = 2; player1.add(15); player.text = playerAI!!.name; AiMove()
            }
            btn15.isClickable = false
            checkWinner()
        }

        btn16.setOnClickListener {
            if(activePlayer == 1){
                btn16.text = "X"; activePlayer = 2; player1.add(16); player.text = playerAI!!.name; AiMove()
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
            AiFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(Player1Arg, param1)
                    putSerializable(Player2Arg, param2)
                }
            }
    }
}
