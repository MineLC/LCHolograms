package lc.minelc.lchologram.objets;

import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.World;

public class HoloEntity extends EntityLiving {

    public HoloEntity(World world) {
        super(world);
    }

    @Override
    public ItemStack bA() {
        return null;
    }

    @Override
    public ItemStack getEquipment(int i) {
        return null;
    }

    @Override
    public void setEquipment(int i, ItemStack itemStack) {}

    @Override
    public ItemStack[] getEquipment() {
        return null;
    }
}
