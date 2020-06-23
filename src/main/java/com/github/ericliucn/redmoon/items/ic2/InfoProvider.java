package com.github.ericliucn.redmoon.items.ic2;

import com.github.ericliucn.redmoon.items.ItemEnergyStone;
import ic2.api.info.IInfoProvider;
import ic2.core.util.ItemInfo;
import net.minecraft.item.ItemStack;

public class InfoProvider extends ItemInfo {

    @Override
    public double getEnergyValue(ItemStack stack) {
        if (stack.getItem() instanceof ItemEnergyStone){
            int meta = stack.getMetadata();
            switch (meta){
                case 0:
                    return 20000D;
                case 1:
                    return 200000D;
                case 2:
                    return 10000000D;
                case 3:
                    return 20000000D;
                case 4:
                    return 40000000D;
            }
        }
        return super.getEnergyValue(stack);
    }
}
