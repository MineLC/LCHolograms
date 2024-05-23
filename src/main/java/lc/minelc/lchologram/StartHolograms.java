package lc.minelc.lchologram;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.tinylog.Logger;

import com.electronwill.nightconfig.core.AbstractCommentedConfig;
import com.electronwill.nightconfig.core.file.FileConfig;
import lc.minelc.lchologram.storage.StaticHologram;
import lc.minelc.lchologram.storage.Hologram;
import lc.minelc.lchologram.storage.HologramStorage;
import lc.minelc.lchologram.storage.ModifiableHologram;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import lc.minelc.lchologram.lines.HologramLine;
import lc.minelc.lchologram.lines.types.BasicHologramLine;
import lc.minelc.lchologram.lines.types.EntityHologramLine;

import lc.lcspigot.entities.CreatureTypes;
import net.md_5.bungee.api.ChatColor;

public final class StartHolograms {

    public void start(final LcHologram plugin) {
        final File file = new File(plugin.getDataFolder(), "holograms.toml");
        if (!file.exists()) {
            plugin.saveResource("holograms.toml", false);
        }
        final FileConfig config = FileConfig.of(file);
		config.load();

        final List<Object> activeHolograms = config.getRaw("active-holograms");
        if (activeHolograms == null) {
            Logger.info("No holograms founded!");
            HologramStorage.resetHolograms(new HashMap<>(), new TIntObjectHashMap<>());
            config.close();
            return;
        }
        final Map<String, Hologram> holograms = HashMap.newHashMap(activeHolograms.size());
        final TIntObjectMap<Hologram> hologramsPerEntity = new TIntObjectHashMap<>(activeHolograms.size());
        int hologramID = Integer.MAX_VALUE + 2;

        for (final Object hologram : activeHolograms) {
            if (!(hologram instanceof String hologramPath)) {
                Logger.warn("Can't load the hologram " + hologram.toString());
                continue;
            }
            final AbstractCommentedConfig hologramSection = config.get(hologramPath);
            final HologramLine[] lines = getLines(hologramSection.getRaw("lines"), hologramID);

            final Boolean isModifiable = config.get("isModifiable");
            final Hologram finalHologram = (isModifiable == null || !isModifiable)
                ? getStaticHologram(hologramSection, lines)
                : new ModifiableHologram(lines);
            
            holograms.put(hologramPath, finalHologram);

            for (int i = 0; i < lines.length; i++) {
                hologramsPerEntity.put(hologramID + i, finalHologram);
            }
            hologramID += lines.length + 1;
        }

        HologramStorage.resetHolograms(holograms, hologramsPerEntity);
        config.close();
    }

    private StaticHologram getStaticHologram(final AbstractCommentedConfig config, final HologramLine[] lines) {
        final Object locationObject = config.get("location");
        World world = null;
        double x = 0, y = 0, z = 0;
        if (locationObject != null && locationObject instanceof AbstractCommentedConfig location) {
            final Number locationX = location.get("x"), locationY = location.get("y"), locationZ = location.get("z");
            x = (locationX != null) ? locationX.doubleValue() : 0;
            y = (locationY != null) ? locationY.doubleValue() : 0;
            z = (locationZ != null) ? locationZ.doubleValue() : 0;
            final String worldName = location.get("world");
            if (worldName != null) {
                world = Bukkit.getWorld(worldName);
            }
        }

        if (world == null) {
            world = Bukkit.getWorlds().get(0);
        }
        return new StaticHologram(lines, x, y, z, world);
    }

    private HologramLine[] getLines(List<Object> lines, int hologramID) {
        if (lines == null) {
            lines = List.of("Default line");
        }
        final HologramLine[] parsedLines = new HologramLine[lines.size()];

        for (int i = 0; i < parsedLines.length; i++) {
            final Object line = lines.get(i);
            if (!(line instanceof AbstractCommentedConfig section)) {
                parsedLines[i] = new BasicHologramLine(line.toString().replace('&', ChatColor.COLOR_CHAR), hologramID++, 0.3F);
                continue;
            }
            final String lineMessage = section.get("line");
            final Number separation = section.get("separation");
            final String entity = section.get("entity");

            Integer entityID = CreatureTypes.getEntityType(entity);
            if (entityID == null) {
                entityID = 30;
            }

            parsedLines[i] = new EntityHologramLine(
                (lineMessage == null) ? "" : lineMessage.replace('&', ChatColor.COLOR_CHAR),
                (separation == null) ? 0.3F : separation.floatValue(),
                hologramID++,
                entityID
            );
        }
        return parsedLines;
    }
    
}