package com.github.ericliucn.redmoon.items;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.utils.Ref;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import vazkii.arl.item.ItemMod;

public class ItemMainGUI extends ItemMod {

    public ItemMainGUI(String name, String... variants) {
        super(name, variants);
        setCreativeTab(Main.creativeTab);
        setMaxDamage(0);
        setNoRepair();
    }

    @Override
    public String getModNamespace() {
        return Main.MOD_ID;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (worldIn.isRemote){
            Main.PROXY.openGUI(Ref.MAIN_MENU);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
