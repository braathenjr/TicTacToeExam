package com.example.exam_4x4tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_start_screen.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    val manager = supportFragmentManager
    var aiPlayer = Player("TTTbot", 0)
    var activeFragment: Int = 0
    lateinit var db: PlayerRoomDatabase
    var playerList = arrayListOf<Player>()
    var selectedQuery: Int = 0
    lateinit var playerWon: Player
    lateinit var adapter: BaseAdapter
    lateinit var list: ListView
    lateinit var gameFragment: GameFragment
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, PlayerRoomDatabase::class.java, "player.db").build()
        //getList().execute()
        //refreshPlayerList(null)

        val startBtn: Button = this.findViewById(R.id.startBtn)
        val startAI: Button = this.findViewById(R.id.startAI)

        startBtn.setOnClickListener(this)
        startAI.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.startBtn -> {

                gameFragment = GameFragment.newInstance("player1", "player2")
                manager.beginTransaction().replace(R.id.fragment2, gameFragment).addToBackStack(null).commit()
                activeFragment = 1
                viewInvisible()

            }
        }
    }
/*
    fun launchNormalGame (){
        gameFragment = GameFragment.newInstance("player1", "player2")
        val transaction =  manager.beginTransaction()
        transaction.replace(R.id.fragment2,gameFragment)
        transaction.addToBackStack(null)
        transaction.commit()
        activeFragment = 1

    }*/

    fun viewInvisible(){
        startBtn.visibility = View.INVISIBLE
        startAI.visibility = View.INVISIBLE
        player1Name.isEnabled = false
        player2Name.isEnabled = false
        player1Name.visibility = View.INVISIBLE
        player2Name.visibility = View.INVISIBLE
    }


    fun showView(){
        startBtn.visibility = View.VISIBLE
        startAI.visibility = View.VISIBLE
        player1Name.isEnabled = true
        player2Name.isEnabled = true
        player1Name.visibility = View.VISIBLE
        player2Name.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        if (activeFragment == 1){
            activeFragment = 0
            showView()
            super.onBackPressed()
        }else{
            super.onBackPressed()
        }


    }
}
