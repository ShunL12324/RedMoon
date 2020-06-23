package com.github.ericliucn.redmoon.items;

import com.github.ericliucn.redmoon.Main;
import net.minecraft.item.ItemStack;
import vazkii.arl.item.ItemMod;

public class ItemGaiaPass extends ItemMod {

    public ItemGaiaPass(String name, String... variants) {
        super(name, variants);
        this.setMaxDamage(0);
        this.setCreativeTab(Main.creativeTab);
    }

    @Override
    public String getModNamespace() {
        return Main.MOD_ID;
    }


}
