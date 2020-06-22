package com.github.ericliucn.redmoon.blocks.tiles;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.*;
import ic2.api.info.ILocatable;
import ic2.api.info.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import vazkii.arl.block.tile.TileSimpleInventory;

public class TileGenerator extends TileSimpleInventory implements ILocatable, IMultiEnergySource,IEnergyAcceptor, ITickable{

    public int storage;
    private boolean addedToEnet;
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
        if (!addedToEnet
                && !getWorldObj().isRemote
                && Info.isIc2Available()) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            addedToEnet = true;
        }
    }

    @Override
    public void onChunkUnload() {
        if (addedToEnet
                && !getWorldObj().isRemote
                && Info.isIc2Available()) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));

            addedToEnet = false;
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        this.onChunkUnload();
    }

    @Override
    public void update() {
        if (!addedToEnet){
            onLoad();
        }
    }


    @Override
    public boolean sendMultipleEnergyPackets() {
        return true;
    }

    @Override
    public int getMultipleEnergyPacketAmount() {
        return this.outPut/30000 + 1;
    }

    @Override
    public BlockPos getPosition() {
        return this.getPos();
    }

    @Override
    public World getWorldObj() {
        return this.world;
    }
}
