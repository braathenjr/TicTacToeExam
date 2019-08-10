package com.example.exam_4x4tictactoe

import android.arch.persistence.room.Room
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.BaseAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.fragment_start_screen.*

class MainActivity : AppCompatActivity(), View.OnClickListener, GameFragment.OnDataPass {


    val manager = supportFragmentManager
    var activeFragment: Int = 0
    lateinit var gf: GameFragment
    lateinit var db: PlayerRoomDatabase
    var playerList = arrayListOf<Player>()
    var selectedQuery: Int = 0
    lateinit var playerWon: Player
    lateinit var baseAdapter: BaseAdapter
    lateinit var list: ListView
    var playerAI = Player("TTTbot", 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, PlayerRoomDatabase::class.java, "player.db").build()
        getList().execute()
        refreshPlayerList(null)

        list = findViewById(R.id.listView)
        startBtn.setOnClickListener(this)

        startAI.setOnClickListener(this)

    }

    internal inner class getList : AsyncTask<Player, Void, List<Player>>(){


        override fun doInBackground(vararg params: Player?): List<Player>? {

            playerList.clear()
            if (selectedQuery == 1){
                db.playerDao().insert(playerWon)
                selectedQuery = 0


            } else if (selectedQuery == 2){
                db.playerDao().updateScore(playerWon.name)
                selectedQuery = 0

            } else if (selectedQuery == 3){
                db.playerDao().insert(playerAI)
                selectedQuery = 0
            }

            return db.playerDao().getAllPlayers()

        }
        override fun onPostExecute(inputList: List<Player>) {
            for (player in inputList){
                playerList.add(player)
            }
            baseAdapter = PlayerListAdapter(this@MainActivity, playerList)
            list.adapter = baseAdapter
            baseAdapter.notifyDataSetChanged()
        }

    }


    override fun onClick(v: View) {
        var Player1 = Player(player1Name.text.toString(), 0)
        var Player2 = Player(player2Name.text.toString(), 0)
        startBtn.setOnClickListener {
            gf = GameFragment.newInstance(Player1, Player2)
            manager.beginTransaction().replace(R.id.homeScreenFragment, gf).addToBackStack(null).commit()
            activeFragment = 1
            viewInvisible()
        }
        startAI.setOnClickListener { 
            gf = GameFragment.newInstance(Player1, playerAI)
            manager.beginTransaction().replace(R.id.homeScreenFragment, gf).addToBackStack(null).commit()
            activeFragment = 1
            viewInvisible() 
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

    private fun pickQuery(playerWon: Player){
        var namesList = arrayListOf<String>()
        for (p in playerList){
            namesList.add(p.name)
        }
        selectedQuery = if (!namesList.contains(playerWon.name)){
            1
        } else {
            2
        }
        refreshPlayerList(playerWon)
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
     override fun onPassData(player: Player) {
        playerWon = player
        pickQuery(playerWon)

    }

    private fun refreshPlayerList(player: Player?){
        getList().execute(player)
    }
}
