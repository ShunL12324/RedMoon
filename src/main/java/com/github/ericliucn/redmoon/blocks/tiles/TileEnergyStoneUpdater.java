package com.github.ericliucn.redmoon.blocks.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import vazkii.arl.block.tile.TileSimpleInventory;

public class TileEnergyStoneUpdater extends TileSimpleInventory implements ITickable {


    @Override
    public int getSizeInventory() {
        return 6;
    }

    @Override
    public void update() {

    }
}
