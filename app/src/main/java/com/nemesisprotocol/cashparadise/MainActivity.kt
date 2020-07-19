package com.nemesisprotocol.cashparadise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    private var score: Int = 0

    lateinit var cashHandler: Handler

    /**
     * Runnable to call incrementCash method to increase users cash every second
     */
    private val updateCash = object : Runnable {
        override fun run() {
            incrementCash()
            cashHandler.postDelayed(this, 1000)
        }
    }

    override fun onPause() {
        super.onPause()
        cashHandler.removeCallbacks(updateCash)
    }

    override fun onResume() {
        super.onResume()
        cashHandler.post(updateCash)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cashHandler = Handler(Looper.getMainLooper())
    }

    /**
     *  Sets UI functionality for activity
     */
    private fun setupUI(){
        cash_increase_iv.setOnClickListener {
            score += 5
            player_score_tv.text = score.toString()
        }
    }

    private fun incrementCash() {
        score += 1
        player_score_tv.text = score.toString()
    }
}