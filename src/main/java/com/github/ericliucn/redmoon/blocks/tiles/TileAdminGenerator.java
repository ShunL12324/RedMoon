package com.github.ericliucn.redmoon.blocks.tiles;


import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IMultiEnergySource;
import ic2.api.tile.IEnergyStorage;
import net.minecraft.util.EnumFacing;

public class TileAdminGenerator extends TileBase implements IEnergyStorage, IMultiEnergySource{

    public int stored = 400000000;
    public int tier = 6;
    public int outPut = 100000;
    public int inPut = 100000;
    public int capacity = 800000000;

    @Override
    public double getOfferedEnergy() {
        return this.stored;
    }

    @Override
    public void drawEnergy(double v) {

    }

    @Override
    public int getSourceTier() {
        return this.tier;
    }

    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor iEnergyAcceptor, EnumFacing enumFacing) {
        return enumFacing == EnumFacing.EAST || enumFacing == EnumFacing.WEST;
    }

    @Override
    public int getStored() {
        return this.stored;
    }

    @Override
    public void setStored(int i) {
        this.stored = i;
    }

    @Override
    public int addEnergy(int i) {
        return this.inPut;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getOutput() {
        return this.outPut;
    }

    @Override
    public double getOutputEnergyUnitsPerTick() {
        return this.outPut;
    }

    @Override
    public boolean isTeleporterCompatible(EnumFacing enumFacing) {
        return false;
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
