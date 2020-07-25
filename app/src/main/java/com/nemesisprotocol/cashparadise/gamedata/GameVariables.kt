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
     * The value for the cost of the Real Estate upgrade at the start of the game
     */
    private const val REAL_ESTATE_UPGRADE_START_COST: Int = 10000000

    /**
     * The value for the cost of the Cash Trident upgrade at the start of the game
     */
    private const val CASH_TRIDENT_UPGRADE_START_COST: Long = 1000000000

    /**
     * The value for the cost of the Lost Treasure upgrade at the start of the game
     */
    private const val LOST_TREASURE_UPGRADE_START_COST: Long = 100000000000

    /**
     * The value for the cost of the Magic Pearls upgrade at the start of the game
     */
    private const val MAGIC_PEARLS_UPGRADE_START_COST: Long = 10000000000000

    /**
     * Title of the Luck upgrade
     */
    const val LUCK_UPGRADE_TITLE: String = "Luck"

    /**
     * Title of the Money Trees upgrade
     */
    const val MONEY_TREES_UPGRADE_TITLE: String = "Money Trees"

    /**
     * Title of the Investments upgrade
     */
    const val INVESTMENTS_UPGRADE_TITLE: String = "Investments"

    /**
     * Title of the Gold Mine upgrade
     */
    const val GOLD_MINE_UPGRADE_TITLE: String = "Gold Mine"

    /**
     * Title of the Real Estate upgrade
     */
    const val REAL_ESTATE_UPGRADE_TITLE: String = "Real Estate"

    /**
     * Title of the Cash Trident upgrade
     */
    const val CASH_TRIDENT_UPGRADE_TITLE: String = "Cash Trident"

    /**
     * Title of the Lost Treasure upgrade
     */
    const val LOST_TREASURE_UPGRADE_TITLE: String = "Lost Treasure"

    /**
     * Title of the Magic Pearls upgrade
     */
    const val MAGIC_PEARLS_UPGRADE_TITLE: String = "Magic Pearls"

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
     * The value to check if player has cash over 1 million so numbers can be reformatted
     * to suit 1.2M 2.5B type formats to improve readability
     */
    const val ONE_MILLION_CASH_VALUE: Int = 1000000

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
     * Upgrade ID for the Real Estate upgrade
     */
    const val REAL_ESTATE_UPGRADE_ID: Int = 5

    /**
     * Upgrade ID for the Cash Trident upgrade
     */
    const val CASH_TRIDENT_UPGRADE_ID: Int = 6

    /**
     * Upgrade ID for the Lost Treasure upgrade
     */
    const val LOST_TREASURE_UPGRADE_ID: Int = 7

    /**
     * Upgrade ID for the Magic Pearls upgrade
     */
    const val MAGIC_PEARLS_UPGRADE_ID: Int = 8

    /**
     * Value for how much the cash over time increments by when Investments upgrade is selected
     */
    const val INVESTMENTS_CASH_OVER_TIME_INCREMENT = 50

    /**
     * Value for how much the cash over time increments by when Gold Mine upgrade is selected
     */
    const val GOLD_MINE_CASH_OVER_TIME_INCREMENT = 1500

    /**
     * Value for how much the cash over time increments by when Real Estate upgrade is selected
     */
    const val REAL_ESTATE_CASH_OVER_TIME_INCREMENT = 25000

    /**
     * Value for how much the cash click increases by when Cash Trident upgrade is selected
     */
    const val CASH_TRIDENT_CASH_CLICK_INCREMENT = 500000

    /**
     * Value for how much the cash over time increments by when Lost Treasure upgrade is selected
     */
    const val LOST_TREASURE_CASH_OVER_TIME_INCREMENT = 5000000

    /**
     * Value for how much the cash over time increments by when Magic Pearls upgrade is selected
     */
    const val MAGIC_PEARLS_CASH_OVER_TIME_INCREMENT = 50000000

    /**
     * Value that undergoes Modulo operation with selected upgrade level to check that the user
     * reached cash bonus of upgrading 10 times. This bonus resets each 10 levels e.g.
     * bonus given at upgrade level 10,20,30 and so forth
     */
    const val LEVEL_BONUS_REACHED = 10

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
     * The value for the cost of the Real Estate upgrade that starts at its given start cost
     */
    var realEstateUpgradeCost: Long = REAL_ESTATE_UPGRADE_START_COST.toLong()

    /**
     * The value for the cost of the Cash Trident upgrade that starts at its given start cost
     */
    var cashTridentUpgradeCost: Long = CASH_TRIDENT_UPGRADE_START_COST

    /**
     * The value for the cost of the Lost Treasure upgrade that starts at its given start cost
     */
    var lostTreasureUpgradeCost: Long = LOST_TREASURE_UPGRADE_START_COST

    /**
     * The value for the cost of the Magic Pearls upgrade that starts at its given start cost
     */
    var magicPearlsUpgradeCost: Long = MAGIC_PEARLS_UPGRADE_START_COST

    /**
     * The Upgrades of the game mapped to their Upgrade IDs
     */
    val GAME_UPGRADES: HashMap<Int, String> =
        hashMapOf(
            LUCK_UPGRADE_ID to LUCK_UPGRADE_TITLE,
            MONEY_TREES_UPGRADE_ID to MONEY_TREES_UPGRADE_TITLE,
            INVESTMENTS_UPGRADE_ID to INVESTMENTS_UPGRADE_TITLE,
            GOLD_MINE_UPGRADE_ID to GOLD_MINE_UPGRADE_TITLE,
            REAL_ESTATE_UPGRADE_ID to REAL_ESTATE_UPGRADE_TITLE,
            CASH_TRIDENT_UPGRADE_ID to CASH_TRIDENT_UPGRADE_TITLE,
            LOST_TREASURE_UPGRADE_ID to LOST_TREASURE_UPGRADE_TITLE,
            MAGIC_PEARLS_UPGRADE_ID to MAGIC_PEARLS_UPGRADE_TITLE
        )
}
