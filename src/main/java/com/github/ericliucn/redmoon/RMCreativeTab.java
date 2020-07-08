package com.github.ericliucn.redmoon;

import com.github.ericliucn.redmoon.items.ModItem;
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
        return new ItemStack(ModItem.ITEM_ENERGY_STONE, 1, 4);
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> p_78018_1_) {
        super.displayAllRelevantItems(p_78018_1_);
    }
}
