package com.nemesisprotocol.cashparadise.adapter

import android.util.Log
import androidx.core.content.ContextCompat
import com.nemesisprotocol.cashparadise.R
import com.nemesisprotocol.cashparadise.gamedata.GameVariables
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import humanize.ICUHumanize
import kotlinx.android.synthetic.main.upgrade_row.view.*
import humanize.ICUHumanize.compactDecimal

/**
 * @property upgradeTitle String is the title of the upgrade
 * @property currentUpgradeLevel String is the current upgrade level the user is on
 * @property currentUpgradeCost Long is the current cost of the upgrade
 * @property upgradeItemDrawableId Int the drawable id of the image for the upgrade
 * @property upgradeId Int is id of the upgrade that has been selected by the user
 * @property currentUpgradeLevelText String the text for text view displaying current upgrade level
 * @property currentUpgradeCostText String for text view displaying current upgrade cost
 * @constructor
 */
class UpgradesAdapter(
    private var upgradeTitle: String,
    private var currentUpgradeLevel: Int,
    private var currentUpgradeCost: Long,
    private val upgradeItemDrawableId: Int,
    private val upgradeId: Int
) :
    Item<GroupieViewHolder>() {
    private var currentUpgradeLevelText = "Upgrade Level $currentUpgradeLevel"
    private var currentUpgradeCostText = "Cost: $currentUpgradeCost"

    /**
     *
     * @return Int returns layout for recycler view
     */
    override fun getLayout(): Int {
        return R.layout.upgrade_row
    }

    /**
     *
     * @param viewHolder GroupieViewHolder is the view holder for the layout within the row of the
     * recycler view
     * @param position Int is the current position of the item within the recycler view
     */
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        /**
         * Setting value of text views within the view
         */
        viewHolder.itemView.tv_upgrade_title.text = upgradeTitle
        viewHolder.itemView.tv_current_upgrade_level.text = currentUpgradeLevelText
        viewHolder.itemView.tv_current_upgrade_cost.text = currentUpgradeCostText

        /**
         * Setting image view of the upgrade item within the recycler view
         */
        viewHolder.itemView.iv_upgrade.setImageDrawable(
            ContextCompat.getDrawable(
                viewHolder.itemView.context,
                upgradeItemDrawableId
            )
        )

        /**
         * On click listener for buying upgrade
         */
        viewHolder.itemView.upgrade_card.setOnClickListener {
            if (GameVariables.cash >= currentUpgradeCost) {
                upgradeSelectedItem(viewHolder)
            }
        }

        /**
         * Displaying cost of upgrade
         */
        checkUpgradeCostFormat(viewHolder)
    }


    /**
     * The benefits of the upgrade selected by the user is added and the upgrade level is
     * increased
     * @param viewHolder GroupieViewHolder is the view holder for the upgrade item within the
     *  recycler view
     */
    private fun upgradeSelectedItem(viewHolder: GroupieViewHolder) {
        currentUpgradeLevel++
        viewHolder.itemView.tv_current_upgrade_level.text = currentUpgradeLevelText.replace(
            currentUpgradeLevelText,
            "Upgrade Level $currentUpgradeLevel",
            true
        )

        /**
         *  Deduct user cash
         */
        GameVariables.cash -= currentUpgradeCost
        currentUpgradeCost += (currentUpgradeCost * GameVariables.UPGRADE_COST_MODIFIER).toLong()

        /**
         *  Replacing current upgrade cost text view with new upgrade cost
         */
        checkUpgradeCostFormat(viewHolder)

        /**
         * Upgrade cash benefits are given determined off what upgrade has been selected
         */
        when (GameVariables.GAME_UPGRADES[upgradeId]) {
            "Luck" -> {
                if (bonusReached()) {
                    GameVariables.cashClickIncrement *= 2
                }
                GameVariables.cashClickIncrement++
            }
            "Money Trees" -> {
                if (bonusReached()) {
                    GameVariables.cashOverTimeIncrement *= 2
                }
                GameVariables.cashOverTimeIncrement++
            }
            "Investments" -> {
                if (bonusReached()) {
                    GameVariables.cashOverTimeIncrement *= 2
                }
                GameVariables.cashOverTimeIncrement += GameVariables
                    .INVESTMENTS_CASH_OVER_TIME_INCREMENT
            }
            "Gold Mine" -> {
                if (bonusReached()) {
                    GameVariables.cashOverTimeIncrement *= 2
                }
                GameVariables.cashOverTimeIncrement += GameVariables
                    .GOLD_MINE_CASH_OVER_TIME_INCREMENT
            }
            "Real Estate" -> {
                if (bonusReached()) {
                    GameVariables.cashOverTimeIncrement *= 2
                }
                GameVariables.cashOverTimeIncrement += GameVariables
                    .REAL_ESTATE_CASH_OVER_TIME_INCREMENT
            }
        }
    }

    /**
     * Checks if the player has reached the bonus level for the upgrade selected where bonus
     * levels are levels that are a multiple of 10
     * @return Boolean returns true if bonus level has been reached
     */
    private fun bonusReached(): Boolean {
        return currentUpgradeLevel % GameVariables.LEVEL_BONUS_REACHED == 0
    }

    /**
     * Checks if cost of the upgrade has exceeded over one million and if so the format of the
     * cost being displayed is changed to a format such as 1.2M, 2.6B and 1.5T where M,B and T
     * represent Million,Billion and Trillion to improve the overall readability of the upgrade
     * cost being displayed else default value of Long Cash value is displayed
     * @param viewHolder GroupieViewHolder of the cash upgrade
     */
    private fun checkUpgradeCostFormat(viewHolder: GroupieViewHolder) {
        if (currentUpgradeCost >= GameVariables.ONE_MILLION_CASH_VALUE) {
            viewHolder.itemView.tv_current_upgrade_cost.text = currentUpgradeCostText.replace(
                currentUpgradeCostText,
                "Cost: ${compactDecimal(currentUpgradeCost)}",
                true
            )
        } else {
            viewHolder.itemView.tv_current_upgrade_cost.text = currentUpgradeCostText.replace(
                currentUpgradeCostText,
                "Cost: $currentUpgradeCost",
                true
            )
        }
    }

    /**
     *
     * @param spanCount Int
     * @param position Int
     * @return Int returns half the span count
     */
    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 2
}
