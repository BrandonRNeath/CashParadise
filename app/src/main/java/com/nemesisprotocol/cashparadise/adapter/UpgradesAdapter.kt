package com.nemesisprotocol.cashparadise.adapter

import androidx.core.content.ContextCompat
import com.nemesisprotocol.cashparadise.R
import com.nemesisprotocol.cashparadise.gamedata.GameVariables
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.upgrade_row.view.*

/**
 *
 * @property currentUpgradeLevel String is the current upgrade level the user is on
 * @property currentUpgradeCost Long is the current cost of the upgrade
 * @property upgradeItemDrawableId Int the drawable id of the image for the upgrade
 * @property upgradeId Int is id of the upgrade that has been selected by the user
 * @property currentUpgradeLevelText String the text for text view displaying current upgrade level
 * @property currentUpgradeCostText String for text view displaying current upgrade cost
 * @constructor
 */
class UpgradesAdapter(
    private var currentUpgradeLevel: Int,
    private var currentUpgradeCost: Long,
    private val upgradeItemDrawableId: Int,
    private val upgradeId: Int
) :
    Item<GroupieViewHolder>() {
    private var currentUpgradeLevelText = "Upgrade Level \n $currentUpgradeLevel"
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

        // Setting value of text views within the view
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
            }
        }
    }

    /**
     *
     * @param viewHolder GroupieViewHolder is the view holder for the upgrade item within the
     *  recycler view
     */
    private fun upgradeSelectedItem(viewHolder: GroupieViewHolder) {
        currentUpgradeLevel++
        viewHolder.itemView.tv_current_upgrade_level.text = currentUpgradeLevelText.replace(
            currentUpgradeLevelText,
            "Upgrade Level \n $currentUpgradeLevel",
            true
        )
        // Deduct user cash
        GameVariables.cash -= currentUpgradeCost
        currentUpgradeCost += (currentUpgradeCost * GameVariables.UPGRADE_COST_MODIFIER).toLong()
        // Replacing current upgrade cost text view with new upgrade cost
        viewHolder.itemView.tv_current_upgrade_cost.text = currentUpgradeCostText.replace(
            currentUpgradeCostText,
            "Cost: $currentUpgradeCost",
            true
        )
        // Upgrade cash benefits are given determined off what upgrade has been selected
        when (GameVariables.GAME_UPGRADES[upgradeId]) {
            "Luck" -> {
                GameVariables.cashClickIncrement++
            }
            "Money Trees" -> {
            }
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
