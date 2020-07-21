package com.nemesisprotocol.cashparadise.gamedata

/**
 * Singleton object for storing global variables for the game
 */
object GameVariables {
    const val START_UPGRADE_LEVEL: Int = 1
    const val UPGRADE_COST_MODIFIER = 0.4
    const val DELAY_ONE_SECOND: Long = 1000
    private const val LUCK_UPGRADE_START_COST: Int = 50
    private const val MONEY_TREES_UPGRADE_START_COST: Int = 100
    val GAME_UPGRADES: HashMap<Int, String> = hashMapOf(1 to "Luck", 2 to "Money Trees")
    var cash: Long = 0
    var cashOverTimeIncrement: Long = 1
    var cashClickIncrement: Long = 1
    var luckUpgradeCost: Long = LUCK_UPGRADE_START_COST.toLong()
    var moneyTreesUpgradeCost: Long = MONEY_TREES_UPGRADE_START_COST.toLong()
}
