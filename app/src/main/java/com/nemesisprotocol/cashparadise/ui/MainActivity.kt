package com.nemesisprotocol.cashparadise.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.nemesisprotocol.cashparadise.R
import com.nemesisprotocol.cashparadise.adapter.UpgradesAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
        private var cash: Long = 0
        private var cashIncrement: Double = 1.0
        private var cashLuckLevel: Int = 1
        private var cashLuckIncreaseCost: Double = 100.0
    }

    private lateinit var upgradeAdapter: GroupAdapter<GroupieViewHolder>

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
        setupUI()
    }

    /**
     *  Sets UI functionality for activity
     */
    private fun setupUI() {

        cash_increase_iv.setOnClickListener {
            cash = (cash + cashIncrement).toLong()
            player_score_tv.text = cash.toString()
        }

        upgradeAdapter = GroupAdapter<GroupieViewHolder>().apply {
            spanCount = 2
        }

        /*
         Adding layout manager for the view as a Grid layout manager. With the addition
         of checking span size of the items within each row of the recycler view
         */
        upgrade_recyclerview.apply {
            layoutManager =
                GridLayoutManager(this@MainActivity, upgradeAdapter.spanCount)
                    .apply {
                        spanSizeLookup = upgradeAdapter.spanSizeLookup
                    }
            adapter = upgradeAdapter
        }
        // Adding a divider between each 2 upgrade items within the recycler view
        upgrade_recyclerview.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        upgradeAdapter.add(UpgradesAdapter(3,1000,R.drawable.luck))
        upgradeAdapter.add(UpgradesAdapter(2,1000,R.drawable.luck))
        upgradeAdapter.add(UpgradesAdapter(3,1000,R.drawable.luck))
        upgradeAdapter.add(UpgradesAdapter(2,1000,R.drawable.luck))
//        cash_click_increase_tv.setOnClickListener {
//            if (cash >= cashLuckIncreaseCost) {
//                cashLuckLevel *= 2
//                cashIncrement++
//                cash = (cash - cashLuckIncreaseCost).toLong()
//                cashLuckIncreaseCost += cashLuckIncreaseCost * 0.4
//            }
//        }
    }

    /**
     *  Overtime cash increment
     */
    private fun incrementCash() {
        cash = (cash + cashIncrement).toLong()
        player_score_tv.text = cash.toString()
    }
}