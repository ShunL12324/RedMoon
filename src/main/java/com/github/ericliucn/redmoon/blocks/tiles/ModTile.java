package com.github.ericliucn.redmoon.blocks.tiles;

import com.github.ericliucn.redmoon.Main;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTile {

    public static void preInit(){
        GameRegistry.registerTileEntity(TileGenerator.class, new ResourceLocation(Main.MOD_ID, "block_generator"));
    }
}
