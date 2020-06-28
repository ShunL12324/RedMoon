package com.github.ericliucn.redmoon.blocks.tiles;


import com.github.ericliucn.redmoon.blocks.slots.InvSlotsCharge;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IMultiEnergySource;
import ic2.api.network.INetworkClientTileEntityEventListener;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.invslot.InvSlot;
import ic2.core.gui.dynamic.DynamicContainer;
import ic2.core.gui.dynamic.DynamicGui;
import ic2.core.gui.dynamic.GuiParser;
import ic2.core.gui.dynamic.IGuiValueProvider;
import ic2.core.network.GuiSynced;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;

public class TileAdminGenerator extends TileBase
        implements
        IMultiEnergySource,
        IHasGui,
        IGuiValueProvider ,
        INetworkClientTileEntityEventListener {

    public int tier = 6;
    @GuiSynced
    public int outPut = 0;
    public InvSlotsCharge slots;

    public TileAdminGenerator(){
        super();
        this.slots = new InvSlotsCharge(this, InvSlot.Access.IO, 4);
    }

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
    protected void updateEntityServer() {
        super.updateEntityServer();
        if (this.outPut < 0) {
            this.outPut = 0;
        }

        this.slots.charge(this.outPut);

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


    @Override
    public ContainerBase<?> getGuiContainer(EntityPlayer entityPlayer) {
        return DynamicContainer.create(this, entityPlayer, GuiParser.parse(this.teBlock));
    }

    @Override
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean b) {
        return DynamicGui.create(this, entityPlayer, GuiParser.parse(this.teBlock));
    }

    @Override
    public void onGuiClosed(EntityPlayer entityPlayer) {

    }

    @Override
    public double getGuiValue(String s) {
        if (s.equals("output")){
            return this.outPut;
        }else {
            return 0;
        }
    }

    @Override
    public void onNetworkEvent(EntityPlayer entityPlayer, int i) {
        if (entityPlayer.canUseCommand(4, "")
                || entityPlayer.isCreative()){
            switch (i){
                case 0:
                    if (this.outPut - 100 >= 0) {
                        this.outPut -= 100;
                    }else {
                        this.outPut = 0;
                    }
                    break;
                case 1:
                    if (this.outPut - 10 >= 0) {
                        this.outPut -= 10;
                    }else {
                        this.outPut = 0;
                    }
                    break;
                case 2:
                    if (this.outPut - 1 >= 0) {
                        this.outPut -= 1;
                    }else {
                        this.outPut = 0;
                    }
                    break;
                case 3:
                    this.outPut += 1;
                    break;
                case 4:
                    this.outPut += 10;
                    break;
                case 5:
                    this.outPut += 100;
                    break;
                case 6:
                    if (this.outPut - 500 >= 0) {
                        this.outPut -= 500;
                    }else {
                        this.outPut = 0;
                    }
                    break;
                case 7:
                    if (this.outPut - 50 >= 0) {
                        this.outPut -= 50;
                    }else {
                        this.outPut = 0;
                    }
                    break;
                case 8:
                    if (this.outPut - 5 >= 0) {
                        this.outPut -= 5;
                    }else {
                        this.outPut = 0;
                    }
                    break;
                case 9:
                    this.outPut += 5;
                    break;
                case 10:
                    this.outPut += 50;
                    break;
                case 11:
                    this.outPut += 500;
                    break;
            }
            this.markDirty();
        }
    }
}
