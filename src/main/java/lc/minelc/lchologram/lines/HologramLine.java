package lc.minelc.lchologram.lines;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.World;

public abstract class HologramLine {

    private final String line;
    private final int hologramID;
    private final float separation;

    protected HologramLine(String line, int hologramID, float separation) {
        this.line = line;
        this.hologramID = hologramID;
        this.separation = separation;
    }

    public float getSeparation() {
        return separation;
    }

    @Override
    public int hashCode() {
        return hologramID;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof HologramLine) ? ((HologramLine)obj).hologramID == this.hologramID : false;
    }

    @Override
    public String toString() {
        return line;
    }

    public abstract Packet<?> getLine(final World world, final double x, final double y, final double z, final int entityID);   
}