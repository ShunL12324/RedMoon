package com.github.ericliucn.redmoon.blocks.tiles;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyTile;
import ic2.core.block.TileEntityInventory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class TileBase extends TileEntityInventory implements IEnergyTile {

    private boolean addToEnet;

    @Override
    protected void onLoaded() {
        super.onLoaded();
        if (!addToEnet && FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            addToEnet = true;
        }
    }

    @Override
    protected void onUnloaded() {
        super.onUnloaded();
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            addToEnet = false;
        }
    }


}
