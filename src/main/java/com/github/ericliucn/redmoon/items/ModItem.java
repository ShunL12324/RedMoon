package com.github.ericliucn.redmoon.items;

import com.github.ericliucn.redmoon.Main;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItem {

    public static final List<Item> ITEMS = new ArrayList<>();

    public static Item ITEM_ENERGY_STONE;

    public static void preInit(){
        ITEM_ENERGY_STONE = new ItemEnergyStone("energy_stone", "energy_stone_common", "energy_stone_good", "energy_stone_excellent", "energy_stone_epic", "energy_stone_legend");
    }
}
