package com.github.ericliucn.redmoon.blocks.containers;

import codechicken.lib.inventory.container.ContainerSynchronised;
import codechicken.lib.inventory.container.SlotDummy;
import codechicken.lib.packet.PacketCustom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import vazkii.arl.container.slot.SlotIngredient;

import javax.annotation.Nonnull;

public class ContainerUpdater extends ContainerSynchronised {

    private final World world;
    private final BlockPos pos;
    private final IItemHandler side;

    public ContainerUpdater(EntityPlayer player, World world, BlockPos pos) {
        this.world = world;
        this.pos = pos;

        TileEntity tile = world.getTileEntity(pos);
        Capability<IItemHandler> capability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

        this.side = tile.getCapability(capability, EnumFacing.NORTH);


        for (int i = 0; i < 3; i++) {
            addSlotToContainer(new SlotItemHandler(this.side, 0, 17, 18));
        }

        for(int i = 0; i < 3; ++i)
            for(int j = 0; j < 9; ++j)
                addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 114 + i * 18));

        for(int k = 0; k < 9; ++k)
            addSlotToContainer(new Slot(player.inventory, k, 8 + k * 18, 172));
    }

    @Override
    public PacketCustom createSyncPacket() {
        return null;
    }

}
