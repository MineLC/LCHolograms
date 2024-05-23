package lc.minelc.lchologram.lines.types;


import lc.minelc.lchologram.lines.HologramLine;
import lc.minelc.lchologram.objets.HoloEntity;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.World;

public class EntityHologramLine extends HologramLine {

    private final int entityID;

    public EntityHologramLine(String line, float separation, int hologramID, int entityID) {
        super(line, hologramID, separation);
        this.entityID = entityID;
    }

    @Override
    public Packet<?> getLine(World world, double x, double y, double z, final int entityID) {
        final HoloEntity entity = new HoloEntity(world);
        entity.d(entityID);
        entity.setCustomName(toString());
        entity.setCustomNameVisible(true);

        entity.locX = x;
        entity.locY = y;
        entity.locZ = z;

        return new PacketPlayOutSpawnEntityLiving(entity, this.entityID);
    }
}