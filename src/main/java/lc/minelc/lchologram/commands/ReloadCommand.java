package lc.minelc.lchologram.commands;

import org.bukkit.command.CommandSender;

import lc.minelc.lchologram.LcHologram;

import lc.lcspigot.commands.Command;

public final class ReloadCommand implements Command {

    private final LcHologram plugin;

    public ReloadCommand(LcHologram plugin) {
        this.plugin = plugin;
    }

    @Override
    public void handle(CommandSender sender, String[] args) {
        if (!checkPermission(sender, "hologram.reload")) {
            return;
        }
        plugin.reloadHolograms();
        sender.sendMessage("Holograms reloaded!");
    }
}