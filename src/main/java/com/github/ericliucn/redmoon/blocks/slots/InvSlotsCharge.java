package com.github.ericliucn.redmoon.blocks.slots;

import ic2.api.energy.tile.IChargingSlot;
import ic2.api.item.ElectricItem;
import ic2.core.block.IInventorySlotHolder;
import ic2.core.block.invslot.InvSlot;
import net.minecraft.item.ItemStack;


public class InvSlotsCharge extends InvSlot implements IChargingSlot {

    public InvSlotsCharge(IInventorySlotHolder<?> base, Access access, int count) {
        super(base, "charge", access, count, InvSide.TOP);
    }

    @Override
    public boolean accepts(ItemStack stack) {
        return ElectricItem.manager.charge(stack, Double.POSITIVE_INFINITY, 6, false, true) > 0;
    }

    @Override
    public double charge(double v) {
        if (v <= 0){
            return 0;
        }

        double charged = 0;
        for(ItemStack itemStack:this){
            if (itemStack != null) {
                double chargeIn = ElectricItem.manager.charge(itemStack, v, 6, true, false);
                charged += chargeIn;
                if (charged >= v){
                    break;
                }
            }
        }

        return charged;
    }
}
