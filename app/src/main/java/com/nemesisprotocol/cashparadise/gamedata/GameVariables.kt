package com.nemesisprotocol.cashparadise.gamedata

/**
 * Singleton object for storing global variables for the game
 */
object GameVariables {
    /**
     * The value for the cost of the Luck upgrade at the start of the game
     */
    private const val LUCK_UPGRADE_START_COST: Int = 50

    /**
     * The value for the cost of Money Trees upgrade at the start of the game
     */
    private const val MONEY_TREES_UPGRADE_START_COST: Int = 100

    /**
     * The value for the cost of Investments upgrade at the start of the game
     */
    private const val INVESTMENTS_UPGRADE_START_COST: Int = 1500

    /**
     * The value for the cost of the Gold Mine upgrade at the start of the game
     */
    private const val GOLD_MINE_UPGRADE_START_COST: Int = 10000

    /**
     * The value for the beginning level of all the upgrades for the game
     */
    const val START_UPGRADE_LEVEL: Int = 1

    /**
     * The value for how much the upgrade cost is multiplied by when upgrade is selected
     */
    const val UPGRADE_COST_MODIFIER = 0.4

    /**
     * The time in millis for the cash over time increment for the game which is every 1 second
     */
    const val DELAY_ONE_SECOND: Long = 1000

    /**
     * Upgrade ID for the Luck upgrade
     */
    const val LUCK_UPGRADE_ID: Int = 1

    /**
     * Upgrade ID for the Money Trees upgrade
     */
    const val MONEY_TREES_UPGRADE_ID: Int = 2

    /**
     * Upgrade ID for the Investments upgrade
     */
    const val INVESTMENTS_UPGRADE_ID: Int = 3

    /**
     * Upgrade ID for the Gold Mine upgrade
     */
    const val GOLD_MINE_UPGRADE_ID: Int = 4

    /**
     * Value for how much the cash over time increments by when Investments upgrade is selected
     */
    const val INVESTMENTS_CASH_OVER_TIME_INCREMENT = 50

    /**
     * Value for how much the cash over time increments by when Investments upgrade is selected
     */
    const val GOLD_MINE_CASH_OVER_TIME_INCREMENT = 1500

    /**
     * Value that undergoes Modulo operation with selected upgrade level to check that the user
     * reached cash bonus of upgrading 10 times. This bonus resets each 10 levels e.g.
     * bonus given at upgrade level 10,20,30 and so forth
     */
    const val LEVEL_BONUS_REACHED = 10

    /**
     * The cash increase per click when the user has reached the bonus
     */
    const val LUCK_CLICK_INCREASE_BONUS = 20

    /**
     * Players game cash which begins at 0 at start of the game
     */
    var cash: Long = 0

    /**
     *  The increment value of the cash per second which begins at 1 at start of the game
     */
    var cashOverTimeIncrement: Long = 1

    /**
     * The cash increment value given per click of the cash which begins at 1 at start of the game
     */
    var cashClickIncrement: Long = 1

    /**
     * The value for the cost of the Luck upgrade that starts at its given start cost
     */
    var luckUpgradeCost: Long = LUCK_UPGRADE_START_COST.toLong()

    /**
     * The value for the cost of the Money Trees upgrade that starts at its given start cost
     */
    var moneyTreesUpgradeCost: Long = MONEY_TREES_UPGRADE_START_COST.toLong()

    /**
     * The value for the cost of the Investments upgrade that starts at its given start cost
     */
    var investmentsUpgradeCost: Long = INVESTMENTS_UPGRADE_START_COST.toLong()

    /**
     * The value for the cost of the Gold Mine upgrade that starts at its given start cost
     */
    var goldMineUpgradeCost: Long = GOLD_MINE_UPGRADE_START_COST.toLong()

    /**
     * The Upgrades of the game mapped to their Upgrade IDs
     */
    val GAME_UPGRADES: HashMap<Int, String> =
        hashMapOf(
            LUCK_UPGRADE_ID to "Luck",
            MONEY_TREES_UPGRADE_ID to "Money Trees",
            INVESTMENTS_UPGRADE_ID to "Investments",
            GOLD_MINE_UPGRADE_ID to "Gold Mine"
        )
}
