package com.github.ericliucn.redmoon.blocks.slots;

import ic2.core.block.IInventorySlotHolder;
import ic2.core.block.invslot.InvSlot;
import net.minecraft.item.ItemStack;

public class InvSlotsIngredient extends InvSlot {

    public InvSlotsIngredient(IInventorySlotHolder<?> base, String name, Access access, int count) {
        super(base, name, access, count);
        this.setStackSizeLimit(1);
    }

    @Override
    public boolean accepts(ItemStack stack) {
        return stack.getItem() instanceof IIngredient;
    }
    
}
