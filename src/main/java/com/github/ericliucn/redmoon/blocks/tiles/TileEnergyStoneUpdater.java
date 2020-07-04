package com.github.ericliucn.redmoon.blocks.tiles;

import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.invslot.InvSlot;
import ic2.core.gui.dynamic.IGuiValueProvider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

public class TileEnergyStoneUpdater extends TileBase implements IEnergySink , IHasGui, IGuiValueProvider, INetworkClientTileEntityEventListener {

    public InvSlot ingredients;
    public InvSlot input;
    public InvSlot outPut;

    public TileEnergyStoneUpdater(){
        super();
        this.ingredients = new InvSlot(this, "ingredients", InvSlot.Access.IO, 4, InvSlot.InvSide.TOP);
        this.ingredients = new InvSlot(this, "ingredients", InvSlot.Access.IO, 4, InvSlot.InvSide.TOP);
    }

    @Override
    public double getDemandedEnergy() {
        return 0;
    }

    @Override
    public int getSinkTier() {
        return 0;
    }

    @Override
    public double injectEnergy(EnumFacing enumFacing, double v, double v1) {
        return 0;
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        return false;
    }

    @Override
    public void onNetworkEvent(EntityPlayer entityPlayer, int i) {

    }

    @Override
    public ContainerBase<?> getGuiContainer(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean b) {
        return null;
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {

    }

    @Override
    public double getGuiValue(String s) {
        return 0;
    }
}
