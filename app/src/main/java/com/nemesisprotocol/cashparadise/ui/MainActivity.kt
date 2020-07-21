package com.nemesisprotocol.cashparadise.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.nemesisprotocol.cashparadise.R
import com.nemesisprotocol.cashparadise.adapter.UpgradesAdapter
import com.nemesisprotocol.cashparadise.gamedata.GameVariables
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var upgradeAdapter: GroupAdapter<GroupieViewHolder>

    lateinit var cashHandler: Handler

    /**
     * Runnable to call incrementCash method to increase users cash every second
     */
    private val updateCash = object : Runnable {
        override fun run() {
            incrementCash()
            cashHandler.postDelayed(this, GameVariables.DELAY_ONE_SECOND)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cashHandler = Handler(Looper.getMainLooper())
        setupUI()
        freshCashParadise()
    }

    /**
     *  Sets UI functionality for activity
     */
    private fun setupUI() {
        cash_increase_iv.setOnClickListener {
            GameVariables.cash = (GameVariables.cash + GameVariables.cashClickIncrement)
            player_score_tv.text = GameVariables.cash.toString()
        }

        // Setting adapter for recycler view for cash upgrades
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
    }

    /**
     *  Overtime cash increment
     */
    private fun incrementCash() {
        GameVariables.cash = (GameVariables.cash + GameVariables.cashOverTimeIncrement)
        player_score_tv.text = GameVariables.cash.toString()
    }

    /**
     *  Setup fresh game
     */
    private fun freshCashParadise() {
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.START_UPGRADE_LEVEL, GameVariables.luckUpgradeCost,
                R.drawable.luck, 1
            )
        )
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.START_UPGRADE_LEVEL, GameVariables.moneyTreesUpgradeCost,
                R.drawable.money_tree, 2
            )
        )
    }

    override fun onPause() {
        super.onPause()
        cashHandler.removeCallbacks(updateCash)
    }

    override fun onResume() {
        super.onResume()
        cashHandler.post(updateCash)
    }
}
