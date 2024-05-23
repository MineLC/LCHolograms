package lc.minelc.lchologram.storage;

import org.bukkit.World;
import org.bukkit.entity.Player;

import lc.minelc.lchologram.lines.HologramLine;

public final class StaticHologram implements Hologram {

    private final double x, y, z;
    private final World world;

    private final HologramLine[] lines;

    public StaticHologram(HologramLine[] lines, double x, double y, double z, World world) {
        this.lines = lines;
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public HologramLine[] getLines() {
        return lines;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public World getWorld() {
        return world;
    }

    public void spawn(final Player player) {
        spawn(x, y, z, world, player);
    }
}