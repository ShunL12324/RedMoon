package com.github.ericliucn.redmoon.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class InventoryHelper {

    public static <T extends EntityPlayer> int itemCount(T player, ItemStack itemStack){
        int count = 0;
        for (ItemStack stack:player.inventory.mainInventory
        ) {
            if (stack.isItemEqual(itemStack)){
                count += stack.getCount();
            }
        }
        return count;
    }

    public static int getFreeSpace(InventoryPlayer inv){
        int free = 0;
        for (ItemStack itemStack:inv.mainInventory
             ) {
            if (itemStack.isEmpty()){
                free += 64;
            }

        }
        return free;
    }

    public static int getFreeSpace(InventoryPlayer inv, ItemStack itemStack){
        int free = 0;
        for (ItemStack stack:inv.mainInventory
        ) {
            if (stack.isEmpty()){
                free += 64;
            }

            if (stack.isItemEqual(itemStack)){
                free += (64 - stack.getCount());
            }

        }
        return free;
    }


}
