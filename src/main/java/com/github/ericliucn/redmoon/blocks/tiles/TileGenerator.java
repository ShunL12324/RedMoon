package com.github.ericliucn.redmoon.blocks.tiles;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import vazkii.arl.block.tile.TileSimpleInventory;

public class TileGenerator extends TileSimpleInventory implements IEnergySource, IEnergyAcceptor, IEnergyTile , ITickable{

    public int storage;
    private boolean addToEnet;

    @Override
    public double getOfferedEnergy() {
        return 10;
    }

    @Override
    public void drawEnergy(double v) {

    }

    @Override
    public int getSourceTier() {
        return 2;
    }

    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor iEnergyAcceptor, EnumFacing enumFacing) {
        return true;
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        return true;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!this.world.isRemote){
            this.addToEnet = !MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
        }
    }

    @Override
    public void update() {
        this.storage += 200;
    }
}
