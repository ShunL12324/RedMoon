package com.github.ericliucn.redmoon.items;

import com.github.ericliucn.redmoon.Main;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.item.Item;
import vazkii.arl.item.ItemMod;

public class ItemCrystal extends ItemMod {

    public ItemCrystal(String name, String... variants) {
        super(name, variants);
        this.setCreativeTab(Main.creativeTab);
        this.setMaxStackSize(64);
    }

    @Override
    public String getModNamespace() {
        return Main.MOD_ID;
    }

}
