package com.github.ericliucn.redmoon.items;

import codechicken.lib.item.ItemMultiType;
import com.github.ericliucn.redmoon.Main;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.arl.item.ItemMod;

public class ItemEnergyStone extends ItemMod {

    public ItemEnergyStone(String name, String... variants) {
        super(name, variants);
        this.setCreativeTab(Main.creativeTab);
        this.setMaxStackSize(64);
    }

    @Override
    public String getModNamespace() {
        return Main.MOD_ID;
    }

}
