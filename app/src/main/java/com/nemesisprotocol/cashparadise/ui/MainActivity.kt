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

    /**
     * This is the group adapter for the recycler view containing the cash upgrades
     */
    private lateinit var upgradeAdapter: GroupAdapter<GroupieViewHolder>

    /**
     * Handler for delaying runnable object every 1 second
     */
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
        supportActionBar?.hide()
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

        /**
         * Setting adapter for recycler view for cash upgrades
         */
        upgradeAdapter = GroupAdapter<GroupieViewHolder>().apply {
            spanCount = 2
        }

        /**
         * Adding layout manager for the view as a Grid layout manager. With the addition
         * of checking span size of the items within each row of the recycler view
         */
        upgrade_recyclerview.apply {
            layoutManager =
                GridLayoutManager(this@MainActivity, upgradeAdapter.spanCount)
                    .apply {
                        spanSizeLookup = upgradeAdapter.spanSizeLookup
                    }
            adapter = upgradeAdapter
        }

        /**
         * Adding a divider between each 2 upgrade items within the recycler view
         */
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
     *  Adds all the upgrades for the game into the adapter for recycler view displaying
     *  upgrades
     */
    private fun freshCashParadise() {
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.START_UPGRADE_LEVEL, GameVariables.luckUpgradeCost,
                R.drawable.luck, GameVariables.LUCK_UPGRADE_ID
            )
        )
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.START_UPGRADE_LEVEL, GameVariables.moneyTreesUpgradeCost,
                R.drawable.money_tree, GameVariables.MONEY_TREES_UPGRADE_ID
            )
        )
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.START_UPGRADE_LEVEL, GameVariables.investmentsUpgradeCost,
                R.drawable.investment, GameVariables.INVESTMENTS_UPGRADE_ID
            )
        )
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.START_UPGRADE_LEVEL, GameVariables.goldMineUpgradeCost,
                R.drawable.goldmine, GameVariables.GOLD_MINE_UPGRADE_ID
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
