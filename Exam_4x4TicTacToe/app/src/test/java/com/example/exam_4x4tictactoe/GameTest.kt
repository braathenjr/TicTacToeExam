package com.example.exam_4x4tictactoe

import org.junit.Assert.assertEquals
import org.junit.Test


class GameTest {
    @Test
    fun CheckPlayerNotWinner(){

        var gf = GameFragment()

        gf.player1.add(2)
        gf.player1.add(6)
        gf.player1.add(13)
        gf.player1.add(4)
        gf.checkWinner()

        assertEquals(0, gf.winner)

    }

}
