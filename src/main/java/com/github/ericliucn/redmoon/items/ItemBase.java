package com.github.ericliucn.redmoon.items;

import com.github.ericliucn.redmoon.Main;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

import java.util.Objects;

public class ItemBase extends Item{

    public ItemBase(String name){
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.setMaxDamage(64);
        ModItem.ITEMS.add(this);
    }

    public void registerModel(){
        ModelResourceLocation location = new ModelResourceLocation(Objects.requireNonNull(this.getRegistryName()), "inventory");
        Main.PROXY.registerItemModel(this, 0, location);
    }


}
