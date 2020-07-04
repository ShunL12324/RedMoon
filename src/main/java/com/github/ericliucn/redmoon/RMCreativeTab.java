package com.github.ericliucn.redmoon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class RMCreativeTab extends CreativeTabs {

    public RMCreativeTab(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.ITEM_FRAME, 1);
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> p_78018_1_) {
        super.displayAllRelevantItems(p_78018_1_);
    }
}
