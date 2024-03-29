package net.runelite.client.plugins.xrunedragons;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.plugins.iutils.*;
import net.runelite.client.plugins.iutils.scripts.UtilsScript;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
public abstract class Task extends UtilsScript {
    // REGIONS AND AREAS
    public final List<Integer> HOME_REGIONS = Arrays.asList(7513, 7514, 7769, 7770);
    public final List<Integer> LITH_REGIONS = Arrays.asList(14242, 6223);
    public final WorldArea edgeVille = new WorldArea(new WorldPoint(3083, 3487, 0), new WorldPoint(3100, 3501, 0));
    public final WorldArea lithkrenTele = new WorldArea(new WorldPoint(3541, 10451, 0), new WorldPoint(3559, 10463, 0));
    public final WorldArea lithkrenDownStairs = new WorldArea(new WorldPoint(3542, 10471, 0), new WorldPoint(3559, 10484, 0));
    public final WorldArea lithkrenBehindDoor = new WorldArea(new WorldPoint(1560, 5060, 0), new WorldPoint(1573, 5082, 0));
    public final WorldArea lithkrenNextToDoor = new WorldArea(new WorldPoint(1573, 5072, 0), new WorldPoint(1572, 5077, 0));
    public final WorldArea runeDragons = new WorldArea(new WorldPoint(1574, 5062, 0), new WorldPoint(1609, 5097, 0));
    @Inject
    public Client client;
    @Inject
    public iUtils utils;
    @Inject
    public CalculationUtils calc;
    @Inject
    public NPCUtils npc;
    @Inject
    public InventoryUtils inventory;
    @Inject
    public PrayerUtils prayerUtils;
    @Inject
    public ObjectUtils object;
    @Inject
    public BankUtils bank;
    @Inject
    public WalkUtils walk;
    @Inject
    public KeyboardUtils keyboard;
    @Inject
    public InterfaceUtils interfaceUtils;
    @Inject
    public MenuUtils menu;
    @Inject
    public MouseUtils mouse;
    public LegacyMenuEntry entry;
    public Set<Integer> EXTENDED_ANTIFIRE_POTS = Set.of(
            ItemID.EXTENDED_ANTIFIRE1,
            ItemID.EXTENDED_ANTIFIRE2,
            ItemID.EXTENDED_ANTIFIRE3,
            ItemID.EXTENDED_ANTIFIRE4
    );
    public Set<Integer> SUPER_EXTENDED_ANTIFIRE_POTS = Set.of(
            ItemID.EXTENDED_SUPER_ANTIFIRE1,
            ItemID.EXTENDED_SUPER_ANTIFIRE2,
            ItemID.EXTENDED_SUPER_ANTIFIRE3,
            ItemID.EXTENDED_SUPER_ANTIFIRE4
    );
    public Set<Integer> PRAYER_POTS = Set.of(
            ItemID.PRAYER_POTION1,
            ItemID.PRAYER_POTION2,
            ItemID.PRAYER_POTION3,
            ItemID.PRAYER_POTION4
    );
    public Set<Integer> SUPER_COMBAT_POTS = Set.of(
            ItemID.SUPER_COMBAT_POTION1,
            ItemID.SUPER_COMBAT_POTION2,
            ItemID.SUPER_COMBAT_POTION3,
            ItemID.SUPER_COMBAT_POTION4
    );
    public Set<Integer> DIVINE_SUPER_COMBAT_POTS = Set.of(
            ItemID.DIVINE_SUPER_COMBAT_POTION1,
            ItemID.DIVINE_SUPER_COMBAT_POTION2,
            ItemID.DIVINE_SUPER_COMBAT_POTION3,
            ItemID.DIVINE_SUPER_COMBAT_POTION4
    );
    public Set<Integer> DIGSITE_PENDANTS = Set.of(
            ItemID.DIGSITE_PENDANT_1,
            ItemID.DIGSITE_PENDANT_2,
            ItemID.DIGSITE_PENDANT_3,
            ItemID.DIGSITE_PENDANT_4,
            ItemID.DIGSITE_PENDANT_5
    );
    public boolean finished = false;
    public boolean started = false;

    public Task() {
    }

    public abstract boolean validate();

    public String getTaskDescription() {
        return this.getClass().getSimpleName();
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isStarted() {
        return started;
    }

    public void checkFinished(GameTick event) {
        return;
    }

    public void onGameTick(GameTick event) {
        return;
    }

    protected long sleepDelay() {
        XRuneDragonsPlugin.sleepLength = calc.randomDelay(XRuneDragonsPlugin.taskConfig.sleepWeightedDistribution(), XRuneDragonsPlugin.taskConfig.sleepMin(), XRuneDragonsPlugin.taskConfig.sleepMax(), XRuneDragonsPlugin.taskConfig.sleepDeviation(), XRuneDragonsPlugin.taskConfig.sleepTarget());
        return XRuneDragonsPlugin.sleepLength;
    }

    protected int tickDelay() {
        XRuneDragonsPlugin.tickLength = (int) calc.randomDelay(XRuneDragonsPlugin.taskConfig.tickDelayWeightedDistribution(), XRuneDragonsPlugin.taskConfig.tickDelayMin(), XRuneDragonsPlugin.taskConfig.tickDelayMax(), XRuneDragonsPlugin.taskConfig.tickDelayDeviation(), XRuneDragonsPlugin.taskConfig.tickDelayTarget());
        return XRuneDragonsPlugin.tickLength;
    }

    protected void useItem(WidgetItem item) {
        if (item != null) {
            entry = new LegacyMenuEntry("", "", item.getId(), MenuAction.ITEM_FIRST_OPTION.getId(), item.getIndex(),
                    WidgetInfo.INVENTORY.getId(), false);
            int sleepTime = calc.getRandomIntBetweenRange(25, 200);
            utils.doActionMsTime(entry, item.getCanvasBounds(), sleepTime);
        }
    }

    protected boolean atPOH() {
        return Arrays.stream(client.getMapRegions()).anyMatch(HOME_REGIONS::contains);
    }

    protected boolean atDragons() {
        return client.getLocalPlayer().getWorldArea().intersectsWith(runeDragons);
    }

    protected boolean atLith() {
        return Arrays.stream(client.getMapRegions()).anyMatch(LITH_REGIONS::contains);
    }

    protected boolean atEdge() {
        return client.getLocalPlayer().getWorldArea().intersectsWith(edgeVille);
    }

    protected boolean shouldRestock(boolean atDragons) {
        if (!inventory.containsItem(XRuneDragonsPlugin.taskConfig.foodID())) {
            if (atDragons) {
                return client.getBoostedSkillLevel(Skill.HITPOINTS) <= XRuneDragonsPlugin.taskConfig.eatMin();
            } else {
                return true;
            }
        }
        if (XRuneDragonsPlugin.taskConfig.superantifire() && !inventory.containsItem(SUPER_EXTENDED_ANTIFIRE_POTS)) {
            if (atDragons) {
                return client.getVarbitValue(6101) == 0;
            } else {
                return true;
            }
        }
        if (!XRuneDragonsPlugin.taskConfig.superantifire() && !inventory.containsItem(EXTENDED_ANTIFIRE_POTS)) {
            if (atDragons || atLith()) {
                return client.getVarbitValue(3981) == 0;
            } else {
                return true;
            }
        }
        if (XRuneDragonsPlugin.taskConfig.supercombats() && !inventory.containsItem(DIVINE_SUPER_COMBAT_POTS)) {
            if (atDragons || atLith()) {
                return client.getBoostedSkillLevel(Skill.STRENGTH) <= XRuneDragonsPlugin.taskConfig.combatMin();
            } else {
                return true;
            }
        }
        if (!XRuneDragonsPlugin.taskConfig.supercombats() && !inventory.containsItem(SUPER_COMBAT_POTS)) {
            if (atDragons || atLith()) {
                return client.getBoostedSkillLevel(Skill.STRENGTH) <= XRuneDragonsPlugin.taskConfig.combatMin();
            } else {
                return true;
            }
        }
        if (!XRuneDragonsPlugin.taskConfig.usePOHdigsite() && !inventory.containsItem(DIGSITE_PENDANTS)) {
            if (atPOH() && !atDragons) {
                return true;
            }
        }
        if (atDragons) {
            return !inventory.containsItem(PRAYER_POTS) && client.getBoostedSkillLevel(Skill.PRAYER) <= XRuneDragonsPlugin.taskConfig.prayerMin();
        } else {
            if (!inventory.containsItem(ItemID.TELEPORT_TO_HOUSE)) {
                return true;
            }
            return !inventory.containsItem(PRAYER_POTS);
        }
    }

    protected NPC findRuneDragon() {
        NPC npcTarget = npc.findNearestAttackableNpcWithin(client.getLocalPlayer().getWorldLocation(), 10, "Rune dragon", true);
        return npcTarget;
    }

    protected int getNpcHealth(NPC npc, Integer max) {
        if (npc == null || npc.getName() == null) {
            return -1;
        }
        int scale = npc.getHealthScale();
        int ratio = npc.getHealthRatio();
        if (ratio < 0 || scale <= 0 || max == null) {
            return -1;
        }
        return (int) ((float) (max * ratio / scale) + 0.5f);
    }

    protected Item getWeapon(int slot) {
        assert client.isClientThread();

        final ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);

        if (equipment == null) {
            return null;
        }

        Item weapon = equipment.getItem(slot);

        return weapon;
    }

}
