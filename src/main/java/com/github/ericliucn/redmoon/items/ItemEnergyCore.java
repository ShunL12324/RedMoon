package com.github.ericliucn.redmoon.items;

import com.github.ericliucn.redmoon.Main;
import vazkii.arl.item.ItemMod;

public class ItemEnergyCore extends ItemMod implements IIngredients {


    public ItemEnergyCore(String name, String... variants) {
        super(name, variants);
        this.setCreativeTab(Main.creativeTab);
        this.setMaxDamage(0);
        this.setNoRepair();
        this.setMaxStackSize(16);
    }

    @Override
    public int getLevel(int meta) {
        return meta;
    }


    @Override
    public String getModNamespace() {
        return Main.MOD_ID;
    }
}
