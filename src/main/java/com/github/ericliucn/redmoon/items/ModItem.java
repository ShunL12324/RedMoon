package com.github.ericliucn.redmoon.items;

import com.github.ericliucn.redmoon.items.ic2.InfoProvider;
import com.github.ericliucn.redmoon.items.tool.BlastPickAxe;
import ic2.api.info.Info;
import net.minecraft.item.Item;

public class ModItem {

    public static Item ITEM_ENERGY_STONE;
    public static Item ITEM_GAIA_PASS;
    public static Item ITEM_BLAST_PICK_AXE;

    public static void preInit(){
        Info.itemInfo = new InfoProvider();
        ITEM_ENERGY_STONE = new ItemEnergyStone(
                "energy_stone",
                "energy_stone_common",
                "energy_stone_good",
                "energy_stone_excellent",
                "energy_stone_epic",
                "energy_stone_legend");

        ITEM_GAIA_PASS = new ItemGaiaPass(
                "gaia_pass",
                "gaia_pass_one",
                "gaia_pass_two"
        );
        ITEM_BLAST_PICK_AXE = new BlastPickAxe(
                "blast_pickaxe",
                Item.ToolMaterial.DIAMOND
        );
    }
}
