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
import kotlinx.android.synthetic.main.fragment_game.*
import java.util.ArrayList

private const val TAG = "GameFragment"


private const val Player1Arg = "Player 1"
private const val Player2Arg = "Player 2"

class GameFragment : Fragment(), View.OnClickListener {

    private var player1Name: String? = null
    private var player2Name: String? = null
  //  private var aiPlayer: Player? = null
    private var playAI: Boolean = false
  //  private var dataPasser: OnDataPass? = null
  //  private val gm = GameManager()
  //  private var tiles = ArrayList<Button>()

    private lateinit var timer: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate starts")
        super.onCreate(savedInstanceState)
   //     player1 = arguments?.getSerializable(Player1Arg) as Player?
   //     if (isPlayingAi){
   //         aiPlayer = arguments?.getSerializable(Player2Arg) as Player?
   //     } else{
   //         player2 = arguments?.getSerializable(Player2Arg) as Player?
   //     }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView starts")
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_game, container, false)


        timer = view.findViewById(R.id.timerView)
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onClick(view: View?){
        val btnSelected = view as Button
        var cellID = 0

        when(btnSelected.id) {
            R.id.btn1 -> cellID = 1
            R.id.btn2 -> cellID = 2
            R.id.btn3 -> cellID = 3
            R.id.btn4 -> cellID = 4
            R.id.btn5 -> cellID = 5
            R.id.btn6 -> cellID = 6
            R.id.btn7 -> cellID = 7
            R.id.btn8 -> cellID = 8
            R.id.btn9 -> cellID = 9
            R.id.btn10 -> cellID = 10
            R.id.btn11 -> cellID = 11
            R.id.btn12 -> cellID = 12
            R.id.btn13 -> cellID = 13
            R.id.btn14 -> cellID = 14
            R.id.btn15 -> cellID = 15
            R.id.btn16 -> cellID = 16

        }
        playGame(cellID,btnSelected)

    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    var activePlayer = 1




    private fun playGame(cellID: Int, btnSelected: Button) {
       // player.text = "player 1"

        if(activePlayer == 1){
            btnSelected.text = "X"

        //    player.text = "Player 2"

            player1.add(cellID)
            activePlayer = 2
            //superAI()
        }
        else if (activePlayer == 2){
            btnSelected.text = "O"
      //      player.text = "Player 1"

            player2.add(cellID)
            activePlayer = 1
        }

        btnSelected.isEnabled = false

        checkWinner()

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

        if(winner != 0){
            if(winner == 1){

                player.text = "Player 1 Wins!"
                //Toast.makeText(this, "Player 1 won!",Toast.LENGTH_SHORT).show()
                timer.stop()
                disableBtn()

            }else if (winner == 2){
                player.text = "Player 2 Wins!"
                // Toast.makeText(this, "Player 2 won!",Toast.LENGTH_LONG).show()
                timer.stop()
                disableBtn()
            }
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


        timer.start()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
     //   listener = null
        timer.stop()
    }
    override fun onStop() {
        Log.d(TAG, "onStop: Called")
        timer.stop()
        super.onStop()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: Called")
        timer.stop()
        super.onPause()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: Called")
        super.onResume()
        timer.start()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GameFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putString(Player1Arg, param1)
                    putString(Player2Arg, param2)
                }
            }
    }
}
