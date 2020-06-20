package com.github.ericliucn.redmoon.items;

import com.github.ericliucn.redmoon.Main;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import vazkii.arl.item.ItemMod;

import java.util.Objects;

public class ItemBase extends ItemMod {

    

    public ItemBase(String name, String... variants) {
        super(name, variants);
    }

    @Override
    public String getModNamespace() {
        return Main.MOD_ID;
    }

    @Override
    public String getUniqueModel() {
        return null;
    }
}
