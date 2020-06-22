package com.github.ericliucn.redmoon.blocks.tiles;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import vazkii.arl.block.tile.TileSimpleInventory;

public class TileGenerator extends TileSimpleInventory implements IMultiEnergySource,IEnergyAcceptor, ITickable{

    public int storage;
    private boolean addToEnet;
    public final int outPut = 32;

    @Override
    public double getOfferedEnergy() {
        return this.outPut;
    }

    @Override
    public void drawEnergy(double v) {

    }

    @Override
    public int getSourceTier() {
        return 6;
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
    public void onChunkUnload() {
        super.onChunkUnload();
        if (!this.world.isRemote){
            this.addToEnet = !MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        }
    }

    @Override
    public void update() {
        this.storage += 200;
    }


    @Override
    public boolean sendMultipleEnergyPackets() {
        return true;
    }

    @Override
    public int getMultipleEnergyPacketAmount() {
        return this.outPut/30000 + 1;
    }
}
