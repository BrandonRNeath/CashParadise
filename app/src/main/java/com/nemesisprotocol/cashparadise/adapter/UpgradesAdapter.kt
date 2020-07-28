package com.nemesisprotocol.cashparadise.adapter

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.nemesisprotocol.cashparadise.R
import com.nemesisprotocol.cashparadise.gamedata.GameVariables
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import humanize.ICUHumanize.compactDecimal
import kotlinx.android.synthetic.main.upgrade_row.view.*

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
        // Setting value of text views within the view
        viewHolder.itemView.tv_upgrade_title.text = upgradeTitle
        viewHolder.itemView.tv_current_upgrade_level.text = currentUpgradeLevelText
        viewHolder.itemView.tv_current_upgrade_cost.text = currentUpgradeCostText

        // Setting image view of the upgrade item within the recycler view
        viewHolder.itemView.iv_upgrade.setImageDrawable(
            ContextCompat.getDrawable(
                viewHolder.itemView.context,
                upgradeItemDrawableId
            )
        )

        // On click listener for buying upgrade
        viewHolder.itemView.upgrade_card.setOnClickListener {
            if (GameVariables.cash >= currentUpgradeCost) {
                upgradeSelectedItem(viewHolder)

                // Animation occurs when upgrade has been bought
                val upgradeClickAnimation: Animation =
                    AnimationUtils.loadAnimation(
                        viewHolder.itemView.context,
                        R.anim.scale_animation
                    )
                viewHolder.itemView.upgrade_card.animation = upgradeClickAnimation
            }
        }

        // Displaying cost of upgrade
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

        // Users cash is deducted
        GameVariables.cash -= currentUpgradeCost
        currentUpgradeCost += (currentUpgradeCost * GameVariables.UPGRADE_COST_MODIFIER).toLong()

        // Replacing current upgrade cost text view with new upgrade cost
        checkUpgradeCostFormat(viewHolder)

        // Upgrade cash benefits are given determined off what upgrade has been selected
        when (GameVariables.GAME_UPGRADES[upgradeId]) {
            GameVariables.LUCK_UPGRADE_TITLE -> {
                GameVariables.cashClickIncrement++
            }

            GameVariables.MONEY_TREES_UPGRADE_TITLE -> {
                GameVariables.cashOverTimeIncrement++
            }

            GameVariables.INVESTMENTS_UPGRADE_TITLE -> {
                GameVariables.cashOverTimeIncrement += GameVariables
                    .INVESTMENTS_CASH_OVER_TIME_INCREMENT
            }

            GameVariables.GOLD_MINE_UPGRADE_TITLE -> {
                GameVariables.cashOverTimeIncrement += GameVariables
                    .GOLD_MINE_CASH_OVER_TIME_INCREMENT
            }

            GameVariables.REAL_ESTATE_UPGRADE_TITLE -> {
                GameVariables.cashOverTimeIncrement += GameVariables
                    .REAL_ESTATE_CASH_OVER_TIME_INCREMENT
            }

            GameVariables.CASH_TRIDENT_UPGRADE_TITLE -> {
                GameVariables.cashClickIncrement += GameVariables
                    .CASH_TRIDENT_CASH_CLICK_INCREMENT
            }

            GameVariables.LOST_TREASURE_UPGRADE_TITLE -> {
                GameVariables.cashOverTimeIncrement += GameVariables
                    .LOST_TREASURE_CASH_OVER_TIME_INCREMENT
            }

            GameVariables.MAGIC_PEARLS_UPGRADE_TITLE -> {
                GameVariables.cashOverTimeIncrement += GameVariables
                    .MAGIC_PEARLS_CASH_OVER_TIME_INCREMENT
            }
        }

        // Bonuses given if user has reached bonus level
        if (bonusReached()) {
            if (GameVariables.GAME_UPGRADES[upgradeId] == GameVariables.LUCK_UPGRADE_TITLE ||
                GameVariables.GAME_UPGRADES[upgradeId] == GameVariables.CASH_TRIDENT_UPGRADE_TITLE
            ) {
                GameVariables.cashClickIncrement *= 2
            } else {
                GameVariables.cashOverTimeIncrement *= 2
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

    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 2
}
