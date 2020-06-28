package com.github.ericliucn.redmoon.items.ic2;

import com.github.ericliucn.redmoon.items.ItemEnergyStone;
import ic2.core.util.ItemInfo;
import net.minecraft.item.ItemStack;

public class InfoProvider extends ItemInfo {

    @Override
    public double getEnergyValue(ItemStack stack) {
        if (stack.getItem() instanceof ItemEnergyStone){
            int meta = stack.getMetadata();
            switch (meta){
                case 0:
                    return 15000D;
                case 1:
                    return 50000D;
                case 2:
                    return 500000D;
                case 3:
                    return 20000000D;
                case 4:
                    return 40000000D;
            }
        }
        return super.getEnergyValue(stack);
    }
}
