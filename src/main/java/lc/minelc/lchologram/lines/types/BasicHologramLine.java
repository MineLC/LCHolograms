package lc.minelc.lchologram.lines.types;

import lc.minelc.lchologram.lines.HologramLine;
import lc.minelc.lchologram.objets.HoloEntity;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.World;

public class BasicHologramLine extends HologramLine {

    public BasicHologramLine(String line, int hologramID, float separation) {
        super(line, hologramID, separation);
    }

    @Override
    public Packet<?> getLine(World world, double x, double y, double z, final int entityID) {
        final HoloEntity entity = new HoloEntity(world);
        entity.setInvisible(true);
        entity.d(entityID);

        entity.setCustomName(toString());
        entity.setCustomNameVisible(true);

        entity.locX = x;
        entity.locY = y;
        entity.locZ = z;
        return new PacketPlayOutSpawnEntityLiving(entity, 30); // Armorstand id = 30
    }
}