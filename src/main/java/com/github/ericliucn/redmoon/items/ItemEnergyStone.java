package com.github.ericliucn.redmoon.items;

import codechicken.lib.item.ItemMultiType;
import com.github.ericliucn.redmoon.Main;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.arl.item.ItemMod;

import javax.annotation.Nullable;
import java.util.List;

public class ItemEnergyStone extends ItemMod {

    public ItemEnergyStone(String name, String... variants) {
        super(name, variants);
        this.setCreativeTab(Main.creativeTab);
        this.setMaxStackSize(64);
    }

    @Override
    public String getModNamespace() {
        return Main.MOD_ID;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int meta = stack.getMetadata();
        switch (meta){
            case 0:
                tooltip.add("- 普通");
                break;
            case 1:
                tooltip.add("- 优质");
                break;
            case 2:
                tooltip.add("- 稀有");
                break;
            case 3:
                tooltip.add("- 史诗");
                break;
            case 4:
                tooltip.add("- 传说");
                break;
        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
