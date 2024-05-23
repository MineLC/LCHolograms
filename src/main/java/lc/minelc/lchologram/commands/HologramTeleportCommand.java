package lc.minelc.lchologram.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lc.minelc.lchologram.storage.Hologram;
import lc.minelc.lchologram.storage.HologramStorage;
import lc.minelc.lchologram.storage.StaticHologram;

import lc.lcspigot.commands.Command;

public final class HologramTeleportCommand implements Command {

    @Override
    public void handle(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sendWithColor(sender, "&cFormat: /tholograms (name)");
            return;
        }
        final Hologram hologram = HologramStorage.getStorage().getHologramsPerName().get(args[0]);
        if (hologram == null) {
            sender.sendMessage("Holograma no encontrado");
            return;
        }
        final Player player = (Player)sender;
        final StaticHologram staticHologram = ((StaticHologram)hologram);
        staticHologram.spawn(player);

        final Location location = new Location(staticHologram.getWorld(), staticHologram.getX(), staticHologram.getY(), staticHologram.getZ());
        player.teleport(location);
        player.sendMessage("Teletransportado");
    }
}