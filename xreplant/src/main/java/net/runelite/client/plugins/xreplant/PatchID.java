package net.runelite.client.plugins.xreplant;

import java.util.HashMap;
import java.util.Map;

public enum PatchID {
    /**
     * Tree patch IDs
     */
    FALADOR_TREE(8389),
    VARROCK_TREE(8390),
    LUMBRIDGE_TREE(8391),
    TAVERLY_TREE(8388),
    GNOME_STRONGHOLD_TREE(19147),
    FARMING_GUILD_TREE(33732),

    /**
     * Fruit tree patch IDs
     */
    CATHERBY_FRUIT_TREE(7965),
    TREE_GNOME_VILLAGE_FRUIT_TREE(7963),
    BRIMHAVEN_FRUIT_TREE(7964),
    GNOME_STRONGHOLD_FRUIT_TREE(7962),
    FARMING_GUILD_FRUIT_TREE(34007),
    PRIFF_FRUIT_TREE(26579),

    /**
     * Menu action triggered when the id is not defined in this class.
     */
    UNKNOWN(-1);

    private static final Map<Integer, PatchID> map = new HashMap<>();

    static
    {
        for (PatchID patchID : values())
        {
            map.put(patchID.getId(), patchID);
        }
    }

    private final int id;

    PatchID(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public static PatchID of(int id)
    {
        return map.getOrDefault(id, UNKNOWN);
    }
}
