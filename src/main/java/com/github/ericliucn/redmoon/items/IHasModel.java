package com.github.ericliucn.redmoon.items;

import com.github.ericliucn.redmoon.Main;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public interface IHasModel {

    default void registerModel(Item item, String name, int meta){
        ModelResourceLocation location = new ModelResourceLocation(name, "inventory");
        Main.PROXY.registerItemModel(item, meta, location);
    }

    default String getName(Item item){
        return item.getRegistryName().getPath();
    }
}
