package lc.minelc.lchologram;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;
import org.tinylog.Logger;

import lc.minelc.lchologram.commands.ReloadCommand;
import lc.minelc.lchologram.commands.HologramTeleportCommand;
import lc.minelc.lchologram.listener.PlayerClickHologramListener;
import lc.minelc.lchologram.storage.HologramStorage;

import gnu.trove.map.hash.TIntObjectHashMap;
import lc.lcspigot.commands.CommandStorage;
import lc.lcspigot.listeners.ListenerRegister;

public final class LcHologram extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        if (!getConfig().getBoolean("load-holograms-file")) {
            Logger.info("No holograms loaded!");
            HologramStorage.resetHolograms(new HashMap<>(), new TIntObjectHashMap<>());
        } else {
            new StartHolograms().start(this);
        }
        CommandStorage.register(new ReloadCommand(this), "rholograms");
        CommandStorage.register(new HologramTeleportCommand(), "tholograms");
        new ListenerRegister(this).register(new PlayerClickHologramListener(), false);
    }

    public void reloadHolograms() {
        new StartHolograms().start(this);
    }
}