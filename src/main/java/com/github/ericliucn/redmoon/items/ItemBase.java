package com.github.ericliucn.redmoon.items;

import com.github.ericliucn.redmoon.Main;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

import java.util.Objects;

public class ItemBase extends Item implements IHasModel{

    public ItemBase(String name){
        this.setRegistryName(name);
        this.setTranslationKey(name);
        ModItem.ITEMS.add(this);
    }

}
