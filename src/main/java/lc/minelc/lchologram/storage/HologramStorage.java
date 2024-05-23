package lc.minelc.lchologram.storage;

import java.util.Map;

import gnu.trove.map.TIntObjectMap;

public final class HologramStorage {
    private static HologramStorage storage;

    private final Map<String, Hologram> hologramsPerName;
    private final TIntObjectMap<Hologram> hologramsPerEntity;

    private HologramStorage(Map<String, Hologram> holograms, TIntObjectMap<Hologram> hologramsPerEntity) {
        this.hologramsPerName = holograms;
        this.hologramsPerEntity = hologramsPerEntity;
    }

    public Map<String, Hologram> getHologramsPerName() {
        return hologramsPerName;
    }

    public TIntObjectMap<Hologram> getHologramsPerEntity() {
        return hologramsPerEntity;
    }

    public static HologramStorage getStorage() {
        return storage;
    }

    public static void resetHolograms(final Map<String, Hologram> newHolograms, final TIntObjectMap<Hologram> hologramsPerEntity) {
        storage = new HologramStorage(newHolograms, hologramsPerEntity);
    }
}