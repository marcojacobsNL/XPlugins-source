package net.runelite.client.plugins.xrunedragons;

import net.runelite.client.config.*;

@ConfigGroup(value = "mruned")
public interface XRuneDragonsConfig
        extends Config {

    @ConfigSection(keyName = "delayConfig", name = "Sleep Delay Configuration", description = "Configure how the bot handles sleep delays", position = 0, closedByDefault = true)
    String delayConfig = "delayConfig";
    @ConfigSection(keyName = "tickConfig", name = "Tick Delay Configuration", description = "Configure how the bot handles tick delays", position = 1, closedByDefault = true)
    String tickConfig = "tickConfig";
    @ConfigSection(keyName = "logicConfig", name = "Logic Configuration", description = "", position = 3)
    String logicConfig = "logicConfig";
    @ConfigSection(keyName = "potionConfig", name = "Potions & Food Configuration", description = "", position = 4)
    String potionConfig = "potionConfig";
    @ConfigSection(keyName = "damageConfig", name = "Damage Configuration", description = "", position = 5)
    String damageConfig = "damageConfig";

    @Range(min = 0, max = 550)
    @ConfigItem(keyName = "sleepMin", name = "Sleep Min", description = "", position = 2, section = "delayConfig")
    default int sleepMin() {
        return 60;
    }

    @Range(min = 0, max = 550)
    @ConfigItem(keyName = "sleepMax", name = "Sleep Max", description = "", position = 3, section = "delayConfig")
    default int sleepMax() {
        return 350;
    }

    @Range(min = 0, max = 550)
    @ConfigItem(keyName = "sleepTarget", name = "Sleep Target", description = "", position = 4, section = "delayConfig")
    default int sleepTarget() {
        return 100;
    }

    @Range(min = 0, max = 550)
    @ConfigItem(keyName = "sleepDeviation", name = "Sleep Deviation", description = "", position = 5, section = "delayConfig")
    default int sleepDeviation() {
        return 10;
    }

    @ConfigItem(keyName = "sleepWeightedDistribution", name = "Sleep Weighted Distribution", description = "Shifts the random distribution towards the lower end at the target, otherwise it will be an even distribution", position = 6, section = "delayConfig")
    default boolean sleepWeightedDistribution() {
        return false;
    }

    @Range(min = 0, max = 10)
    @ConfigItem(keyName = "tickDelayMin", name = "Game Tick Min", description = "", position = 8, section = "tickConfig")
    default int tickDelayMin() {
        return 1;
    }

    @Range(min = 0, max = 10)
    @ConfigItem(keyName = "tickDelayMax", name = "Game Tick Max", description = "", position = 9, section = "tickConfig")
    default int tickDelayMax() {
        return 3;
    }

    @Range(min = 0, max = 10)
    @ConfigItem(keyName = "tickDelayTarget", name = "Game Tick Target", description = "", position = 10, section = "tickConfig")
    default int tickDelayTarget() {
        return 2;
    }

    @Range(min = 0, max = 10)
    @ConfigItem(keyName = "tickDelayDeviation", name = "Game Tick Deviation", description = "", position = 11, section = "tickConfig")
    default int tickDelayDeviation() {
        return 1;
    }

    @ConfigItem(keyName = "tickDelayWeightedDistribution", name = "Game Tick Weighted Distribution", description = "Shifts the random distribution towards the lower end at the target, otherwise it will be an even distribution", position = 12, section = "tickConfig")
    default boolean tickDelayWeightedDistribution() {
        return false;
    }

    @ConfigItem(keyName = "usePOHpool", name = "Drink POH Pool", description = "Enable to drink from POH pool to restore HP / Prayer.", position = 60, section = "logicConfig")
    default boolean usePOHpool() {
        return true;
    }

    @ConfigItem(keyName = "usePOHdigsite", name = "Use POH digsite", description = "Enable to use POH digsite instead of necklace", position = 61, section = "logicConfig")
    default boolean usePOHdigsite() {
        return true;
    }

    @ConfigItem(keyName = "lootValue", name = "Minimum loot value", description = "Provide of minimum GP value to loot", position = 63, section = "logicConfig")
    default int lootValue() {
        return 2000;
    }

    @ConfigItem(keyName = "supercombats", name = "Use (divine) super combats", description = "Enable to use Divine Super Combats. Disable to use regular Super Combat", position = 59, section = "potionConfig")
    default boolean supercombats() {
        return true;
    }

    @Range(min = 70, max = 118)
    @ConfigItem(keyName = "combatMin", name = "(Divine) super combats threshhold", description = "", position = 60, section = "potionConfig")
    default int combatMin() {
        return 99;
    }

    @ConfigItem(keyName = "superantifire", name = "Use extended (super) antifire", description = "Enable to use Extended Super Antifire. Disable to use regular Extended Antifire.", position = 66, section = "potionConfig")
    default boolean superantifire() {
        return true;
    }

    @ConfigItem(keyName = "praypotAmount", name = "Amount of Prayer Potions", description = "Amount of prayer potions to withdraw from the bank", position = 69, section = "potionConfig")
    default int praypotAmount() {
        return 2;
    }

    @Range(min = 0, max = 99)
    @ConfigItem(keyName = "prayerMin", name = "Prayer potions threshhold", description = "", position = 70, section = "potionConfig")
    default int prayerMin() {
        return 20;
    }

    @ConfigItem(keyName = "foodID", name = "Food ID", description = "ID of food to withdraw.", position = 79, section = "potionConfig")
    default int foodID() {
        return 385;
    }

    @ConfigItem(keyName = "foodAmount", name = "Amount of food", description = "Amount of food to withdraw", position = 80, section = "potionConfig")
    default int foodAmount() {
        return 18;
    }

    @Range(min = 0, max = 99)
    @ConfigItem(keyName = "eatMin", name = "Food threshhold", description = "", position = 81, section = "potionConfig")
    default int eatMin() {
        return 70;
    }

    @ConfigItem(keyName = "useVengeance", name = "Use Vengeance", description = "Enable to use Vengeance on purple hitsplat.", position = 85, section = "damageConfig")
    default boolean useVengeance() {
        return false;
    }

    @ConfigItem(keyName = "useSpec", name = "Use Spec Weapon", description = "Enable to use Spec Weapon", position = 90, section = "damageConfig")
    default boolean useSpec() {
        return false;
    }

    @ConfigItem(keyName = "specId", name = "Spec Weapon ID", description = "Spec weapon ID to use.", position = 91, section = "damageConfig")
    default int specId() {
        return 13652;
    }

    @ConfigItem(keyName = "mainId", name = "Main Weapon ID", description = "Main weapon ID to use.", position = 92, section = "damageConfig")
    default int mainId() {
        return 22978;
    }

    @ConfigItem(keyName = "shieldId", name = "Shield ID", description = "Shield weapon ID to use.", position = 93, section = "damageConfig")
    default int shieldId() {
        return 22322;
    }

    @Range(min = 0, max = 100)
    @ConfigItem(keyName = "specTreshhold", name = "Spec threshhold", description = "Amount of spec % before using spec.", position = 96, section = "damageConfig")
    default int specTreshhold() {
        return 70;
    }

    @Range(min = 0, max = 330)
    @ConfigItem(keyName = "specHp", name = "Spec HP", description = "Min amount of HP before using spec.", position = 97, section = "damageConfig")
    default int specHp() {
        return 200;
    }

    @ConfigItem(
            keyName = "startButton",
            name = "Start/Stop",
            description = "Button to start or stop the plugin",
            position = 33
    )
    default Button startButton() {
        return new Button();
    }
}
