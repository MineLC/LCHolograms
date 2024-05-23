package lc.minelc.lchologram.storage;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import lc.minelc.lchologram.lines.HologramLine;

import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.Packet;

public interface Hologram {

    HologramLine[] getLines();

    default void spawn(final double x, final double y, final double z, final World bukkitWorld, final Player bukkitPlayer) {
        final net.minecraft.server.v1_8_R3.World world = ((CraftWorld)bukkitWorld).getHandle();
        final NetworkManager connection = ((CraftPlayer)bukkitPlayer).getHandle().playerConnection.networkManager;
        final HologramLine[] lines = getLines();
        float height = lines[0].getSeparation();

        for (final HologramLine line : lines) {
            height -= line.getSeparation();
            final Packet<?> packetSpawn = line.getLine(world, x, y + height, z, line.hashCode());

            connection.handle(packetSpawn);
        }
    }
}