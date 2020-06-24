package com.github.ericliucn.redmoon.blocks.tiles;


import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IMultiEnergySource;
import ic2.api.tile.IEnergyStorage;
import ic2.core.util.Util;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;

public class TileAdminGenerator extends TileBase implements IMultiEnergySource{

    public int tier = 6;
    public int outPut = 0;

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        this.outPut = nbtTagCompound.getInteger("output");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setTag("output", new NBTTagInt(this.outPut));
        return nbt;
    }


    @Override
    public double getOfferedEnergy() {
        return this.outPut;
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
        return true;
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
