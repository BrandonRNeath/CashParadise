package com.nemesisprotocol.cashparadise.gamedata

/**
 * Singleton object for storing global variables for the game
 */
object GameVariables {
    private const val LUCK_UPGRADE_START_COST: Int = 50
    private const val MONEY_TREES_UPGRADE_START_COST: Int = 100
    private const val INVESTMENTS_UPGRADE_START_COST: Int = 1500
    const val START_UPGRADE_LEVEL: Int = 1
    const val UPGRADE_COST_MODIFIER = 0.4
    const val DELAY_ONE_SECOND: Long = 1000
    const val LUCK_UPGRADE_ID: Int = 1
    const val MONEY_TREES_UPGRADE_ID: Int = 2
    const val INVESTMENTS_UPGRADE_ID: Int = 3
    const val INVESTMENTS_CASH_OVER_TIME_INCREMENT = 10
    var cash: Long = 0
    var cashOverTimeIncrement: Long = 1
    var cashClickIncrement: Long = 1
    var luckUpgradeCost: Long = LUCK_UPGRADE_START_COST.toLong()
    var moneyTreesUpgradeCost: Long = MONEY_TREES_UPGRADE_START_COST.toLong()
    var investmentsUpgradeCost: Long = INVESTMENTS_UPGRADE_START_COST.toLong()
    val GAME_UPGRADES: HashMap<Int, String> =
        hashMapOf(
            LUCK_UPGRADE_ID to "Luck",
            MONEY_TREES_UPGRADE_ID to "Money Trees",
            INVESTMENTS_UPGRADE_ID to "Investments"
        )
}
