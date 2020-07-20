package com.nemesisprotocol.cashparadise.adapter

import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.nemesisprotocol.cashparadise.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.upgrade_row.view.*


/**
 *
 * @property currentUpgradeLevel String is the current upgrade level the user is on
 * @property currentUpgradeCost Long is the current cost of the upgrade
 * @constructor
 */
class UpgradesAdapter(
    private var currentUpgradeLevel: Int,
    private var currentUpgradeCost: Long,
    private val upgradeItemDrawableId: Int
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

        viewHolder.itemView.upgrade_card.setOnClickListener {
            currentUpgradeLevel++
            Log.d("Test", currentUpgradeLevel.toString())

            viewHolder.itemView.tv_current_upgrade_level.text =  currentUpgradeLevelText.replace(
                currentUpgradeLevelText,
                "Upgrade Level \n $currentUpgradeLevel",
                true
            )
        }
    }


    // Make onclick to increase price

    /**
     *
     * @param spanCount Int
     * @param position Int
     * @return Int returns half the span count
     */
    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 2
}