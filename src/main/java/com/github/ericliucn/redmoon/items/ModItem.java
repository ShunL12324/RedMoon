package com.github.ericliucn.redmoon.items;

import ic2.api.info.Info;
import ic2.api.item.ElectricItem;
import net.minecraft.item.Item;

public class ModItem {

    public static Item ITEM_ENERGY_STONE;

    public static void preInit(){
        Info.itemInfo = new InfoProvider();
        ITEM_ENERGY_STONE = new ItemEnergyStone("energy_stone", "energy_stone_common", "energy_stone_good", "energy_stone_excellent", "energy_stone_epic", "energy_stone_legend");
    }
}
