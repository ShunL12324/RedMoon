package com.github.ericliucn.redmoon.blocks.tiles;

import com.github.ericliucn.redmoon.items.IIngredients;
import com.github.ericliucn.redmoon.items.ModItem;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import vazkii.arl.block.tile.TileSimpleInventory;

import javax.annotation.Nonnull;

public class TileEnergyStoneUpdater extends TileSimpleInventory implements ITickable, IEnergyTile, IEnergySink {

    public boolean addNet;
    private int progress;
    public double consumedEnergy;
    public boolean process;
    public int level;
    public int stream;
    private int preStream;



    @Override
    public void writeSharedNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("Progress", this.progress);
        par1NBTTagCompound.setDouble("Consumed", this.consumedEnergy);
        par1NBTTagCompound.setBoolean("Process", this.process);
        par1NBTTagCompound.setInteger("Level", this.level);
        super.writeSharedNBT(par1NBTTagCompound);
    }

    @Override
    public void readSharedNBT(NBTTagCompound par1NBTTagCompound) {
        this.progress = par1NBTTagCompound.getInteger("Progress");
        this.consumedEnergy = par1NBTTagCompound.getDouble("Consumed");
        this.process = par1NBTTagCompound.getBoolean("Process");
        this.level = par1NBTTagCompound.getInteger("Level");
        super.readSharedNBT(par1NBTTagCompound);
    }

    @Override
    public int getSizeInventory() {
        return 6;
    }

    @Override
    public boolean canInsertItem(int index, @Nonnull ItemStack itemStackIn, @Nonnull EnumFacing direction) {
        if (!isAutomationEnabled()) return false;
        if ((itemStackIn.getItem() instanceof IIngredients) && index >= 0 && index <= 3) return true;
        return itemStackIn.getItem().equals(ModItem.ITEM_ENERGY_STONE) && index == 4;
    }

    @Override
    public boolean canExtractItem(int index, @Nonnull ItemStack stack, @Nonnull EnumFacing direction) {
        if(!isAutomationEnabled()) return false;
        return index == 5;
    }

    @Override
    public void update() {

        //launch progress
        if (isValidItems() && !this.process && this.progress == 0 ){
            this.level = this.getStackInSlot(4).getMetadata();
            this.getStackInSlot(0).shrink(1);
            this.getStackInSlot(1).shrink(1);
            this.getStackInSlot(2).shrink(1);
            this.getStackInSlot(3).shrink(1);
            this.getStackInSlot(4).shrink(1);
            this.process = true;
        }

        //is finish?
        if (this.progress >= 10000){
            ItemStack result = this.getStackInSlot(5);
            if (result.isEmpty()){
                this.setInventorySlotContents(5, getResultProduct());
            }else if (result.isItemEqual(getResultProduct()) && result.getCount() < result.getMaxStackSize()){
                result.grow(1);
            }
            this.process = false;
            this.progress = 0;
            this.consumedEnergy = 0;
        }

        this.stream = this.preStream;
        this.preStream = 0;
    }


    private ItemStack getResultProduct(){
        if (this.level < 0) return ItemStack.EMPTY;
        int lev = Math.min(4, this.level);
        return new ItemStack(ModItem.ITEM_ENERGY_STONE, 1, lev + 1);
    }

    public boolean isValidItems(){
        // check energy stone
        ItemStack mainStack = this.inventorySlots.get(4);
        if (mainStack.isEmpty() || !mainStack.getItem().equals(ModItem.ITEM_ENERGY_STONE)) return false;
        level = mainStack.getMetadata();
        if (level == 4) return false;

        // can process launch?
        ItemStack resultStack = this.getStackInSlot(5);
        if (resultStack != ItemStack.EMPTY){
            if (!resultStack.isItemEqual(getResultProduct())){
                return false;
            }else if (resultStack.getCount() >= resultStack.getMaxStackSize()){
                return false;
            }
        }

        // check ingredients
        for (int i = 0; i < 4; i++) {
            ItemStack stack = this.inventorySlots.get(i);
            if (stack.isEmpty() || !(stack.getItem() instanceof IIngredients)) return false;
            if (((IIngredients) stack.getItem()).getLevel(stack.getMetadata()) < level) return false;
        }

        return true;
    }


    @Override
    public double getDemandedEnergy() {
        if (this.process){
            switch (this.level){
                case 0:
                    return (10000 - Math.min(this.progress, 10000))/10000D * getLevelEnergy(0);
                case 1:
                    return (10000 - Math.min(this.progress, 10000))/10000D * getLevelEnergy(1);
                case 2:
                    return (10000 - Math.min(this.progress, 10000))/10000D * getLevelEnergy(2);
                case 3:
                    return (10000 - Math.min(this.progress, 10000))/10000D * getLevelEnergy(3);
            }
        }
        return 0;
    }

    @Override
    public int getSinkTier() {
        return 6;
    }

    @Override
    public double injectEnergy(EnumFacing enumFacing, double v, double v1) {
        if (this.process){
            this.consumedEnergy += v;
            this.progress = (int) Math.round ((this.consumedEnergy / getLevelEnergy(this.level)) * 10000);
            this.preStream +=v;
        }
        return 0;
    }

    public int getStream() {
        return this.process ? stream:0;
    }

    private double getLevelEnergy(int level){
        switch (level){
            case 0:
                return 1000000;
            case 1:
                return 10000000;
            case 2:
                return 100000000;
            case 3:
                return 1000000000;
        }
        return 1000000000;
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        return true;
    }

    public int getProgress() {
        return progress;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!addNet && FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            addNet = true;
        }
    }

    @Override
    public void validate() {
        super.validate();
        if (!addNet && FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
            addNet = true;
        }
    }

    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            addNet = false;
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
            addNet = false;
        }
    }
}
