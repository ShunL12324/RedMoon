package com.github.ericliucn.redmoon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RMCreativeTab extends CreativeTabs {

    public RMCreativeTab(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.ITEM_FRAME, 1);
    }
}
