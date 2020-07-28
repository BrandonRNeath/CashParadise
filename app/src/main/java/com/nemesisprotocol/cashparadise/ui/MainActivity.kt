package com.nemesisprotocol.cashparadise.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.nemesisprotocol.cashparadise.R
import com.nemesisprotocol.cashparadise.adapter.UpgradesAdapter
import com.nemesisprotocol.cashparadise.gamedata.GameVariables
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import humanize.ICUHumanize.compactDecimal
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private var playersTotalCash = "Total Cash: ${GameVariables.cash}"
    private lateinit var upgradeAdapter: GroupAdapter<GroupieViewHolder>
    lateinit var cashHandler: Handler

    /**
     * Runnable to call incrementCash method to increase users cash every second
     */
    private val updateCash = object : Runnable {
        override fun run() {
            incrementCash(GameVariables.cashOverTimeIncrement)
            cashHandler.postDelayed(this, GameVariables.DELAY_ONE_SECOND)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
    }

    /**
     *  Sets UI functionality for activity
     */
    private fun setupUI() {

        cashHandler = Handler(Looper.getMainLooper())

        // Setup on click listener to increase cash each click of cash
        cash_increase_iv.setOnClickListener {
            incrementCash(GameVariables.cashClickIncrement)

            // Animation each time the player clicks the cash
            val cashClickAnimation: Animation =
                AnimationUtils.loadAnimation(this, R.anim.scale_animation)
            cash_increase_iv.startAnimation(cashClickAnimation)
        }

        // Setting adapter for recycler view for cash upgrades
        upgradeAdapter = GroupAdapter<GroupieViewHolder>().apply {
            spanCount = 2
        }

        // Adding layout manager for the view as a Grid layout manager. With the addition
        // of checking span size of the items within each row of the recycler view
        upgrade_recyclerview.apply {
            layoutManager =
                GridLayoutManager(this@MainActivity, upgradeAdapter.spanCount)
                    .apply {
                        spanSizeLookup = upgradeAdapter.spanSizeLookup
                    }
            adapter = upgradeAdapter
        }

        // Begin Cash Paradise game
        freshCashParadise()
    }

    /**
     *
     * Increments the players cash depending on increment value passed whether its cash over time
     * increment value or cash click increment value
     * @param incrementValue Long
     */
    private fun incrementCash(incrementValue: Long) {
        GameVariables.cash = (GameVariables.cash + incrementValue)
        if (GameVariables.cash > GameVariables.ONE_MILLION_CASH_VALUE) {
            player_score_tv.text = playersTotalCash.replace(
                playersTotalCash,
                "Total Cash: ${compactDecimal(GameVariables.cash)}",
                true
            )
        } else {
            player_score_tv.text = playersTotalCash.replace(
                playersTotalCash,
                "Total Cash: ${(GameVariables.cash)}",
                true
            )
        }
    }

    /**
     *  Sets up fresh game
     *  Adds all the upgrades for the game into the adapter for recycler view displaying upgrades
     */
    private fun freshCashParadise() {
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.LUCK_UPGRADE_TITLE,
                GameVariables.START_UPGRADE_LEVEL, GameVariables.luckUpgradeCost,
                R.drawable.luck, GameVariables.LUCK_UPGRADE_ID
            )
        )
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.MONEY_TREES_UPGRADE_TITLE,
                GameVariables.START_UPGRADE_LEVEL, GameVariables.moneyTreesUpgradeCost,
                R.drawable.money_tree, GameVariables.MONEY_TREES_UPGRADE_ID
            )
        )
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.INVESTMENTS_UPGRADE_TITLE,
                GameVariables.START_UPGRADE_LEVEL, GameVariables.investmentsUpgradeCost,
                R.drawable.investment, GameVariables.INVESTMENTS_UPGRADE_ID
            )
        )
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.GOLD_MINE_UPGRADE_TITLE,
                GameVariables.START_UPGRADE_LEVEL, GameVariables.goldMineUpgradeCost,
                R.drawable.goldmine, GameVariables.GOLD_MINE_UPGRADE_ID
            )
        )
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.REAL_ESTATE_UPGRADE_TITLE,
                GameVariables.START_UPGRADE_LEVEL, GameVariables.realEstateUpgradeCost,
                R.drawable.real_estate, GameVariables.REAL_ESTATE_UPGRADE_ID
            )
        )
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.CASH_TRIDENT_UPGRADE_TITLE,
                GameVariables.START_UPGRADE_LEVEL, GameVariables.cashTridentUpgradeCost,
                R.drawable.cash_trident, GameVariables.CASH_TRIDENT_UPGRADE_ID
            )
        )
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.LOST_TREASURE_UPGRADE_TITLE,
                GameVariables.START_UPGRADE_LEVEL, GameVariables.lostTreasureUpgradeCost,
                R.drawable.lost_treasure, GameVariables.LOST_TREASURE_UPGRADE_ID
            )
        )
        upgradeAdapter.add(
            UpgradesAdapter(
                GameVariables.MAGIC_PEARLS_UPGRADE_TITLE,
                GameVariables.START_UPGRADE_LEVEL, GameVariables.magicPearlsUpgradeCost,
                R.drawable.magic_pearls, GameVariables.MAGIC_PEARLS_UPGRADE_ID
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
