package com.github.ericliucn.redmoon.blocks.containers;

import com.github.ericliucn.redmoon.blocks.tiles.TileEnergyStoneUpdater;
import com.github.ericliucn.redmoon.items.IIngredients;
import com.github.ericliucn.redmoon.items.ModItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.arl.container.ContainerBasic;
import vazkii.arl.container.slot.SlotFiltered;

public class ContainerUpdater extends ContainerBasic<TileEnergyStoneUpdater> {

    private int progress;
    private int stream;

    public ContainerUpdater(EntityPlayer player, World world, BlockPos pos) {
        super(player.inventory, ((TileEnergyStoneUpdater) world.getTileEntity(pos)));
    }


    @Override
    public int addSlots() {
        for (int i = 0; i < 3; i++) {
            addSlotToContainer(new SlotFiltered(tile, 0, 21, 14, stack -> stack.getItem() instanceof IIngredients));
            addSlotToContainer(new SlotFiltered(tile, 1, 68, 14, stack -> stack.getItem() instanceof IIngredients));
            addSlotToContainer(new SlotFiltered(tile, 2, 21, 57, stack -> stack.getItem() instanceof IIngredients));
            addSlotToContainer(new SlotFiltered(tile, 3, 68, 57, stack -> stack.getItem() instanceof IIngredients));
            addSlotToContainer(new SlotFiltered(tile, 4, 44, 36, stack -> stack.getItem().equals(ModItem.ITEM_ENERGY_STONE)));
            addSlotToContainer(new SlotFiltered(tile, 5, 134, 36, stack -> false));
        }
        return 6;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (this.tile.getProgress() != this.progress || tile.getStream() != this.stream){
            this.progress = tile.getProgress();
            this.stream = tile.getStream();
            for (IContainerListener listener:this.listeners
                 ) {
                listener.sendWindowProperty(this, 0, this.progress);
                listener.sendWindowProperty(this, 1, this.stream);
            }
        }

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data) {
        if (id == 0){
            this.progress = data;
        }

        if (id == 1){
            this.stream = data;
        }
    }

    public int getProgress() {
        return progress;
    }

    public int getStream() {
        return stream;
    }
}
