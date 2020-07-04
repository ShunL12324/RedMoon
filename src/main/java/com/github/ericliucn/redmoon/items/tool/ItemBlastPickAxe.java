package com.github.ericliucn.redmoon.items.tool;

import com.github.ericliucn.redmoon.Main;
import ic2.core.ref.Materials;
import javafx.scene.paint.Material;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import vazkii.arl.item.ItemModPickaxe;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemBlastPickAxe extends ItemModPickaxe {

    public static final Set<Block> filterBlock = new HashSet<Block>(){{
        add(Blocks.STONE);
        add(Blocks.DIRT);
        add(Blocks.COBBLESTONE);
        add(Blocks.GRASS);
    }};

    public ItemBlastPickAxe(String name, ToolMaterial material, String... variants) {
        super(name, material, variants);
        this.setHarvestLevel("pickaxe", 3);
        this.setMaxDamage(2200);
        this.setCreativeTab(Main.creativeTab);
        this.setMaxStackSize(1);
        this.setNoRepair();
    }

    @Override
    public String getModNamespace() {
        return Main.MOD_ID;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        RayTraceResult result = player.rayTrace(10, 1F);
        if (result == null) return super.onBlockStartBreak(itemstack, pos, player);

        EnumFacing side = result.sideHit;

        boolean doX = side.getXOffset() == 0;
        boolean doY = side.getYOffset() == 0;
        boolean doZ = side.getZOffset() == 0;

        Vec3i start = new Vec3i(doX ? -1 : 0, doY ? -1 : 0, doZ ? -1 : 0);
        Vec3i end = new Vec3i(doX ? 1 : 0, doY ? 1 : 0, doZ ? 1 : 0);

        breakAllBlocksWithDrop(start, end, player, pos, itemstack);

        Block block = player.world.getBlockState(pos).getBlock();
        if (filterBlock.contains(block)){
            player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
            itemstack.damageItem(1, player);
            return true;
        }

        return super.onBlockStartBreak(itemstack, pos, player);
    }

    public void breakAllBlocksWithDrop(Vec3i start, Vec3i end, EntityPlayer player, BlockPos center, ItemStack stack){
        for (BlockPos pos:BlockPos.getAllInBox(center.add(start), center.add(end))){
            if (pos.equals(center)){
                continue;
            }

            if (!player.world.isBlockLoaded(pos)) return;

            IBlockState state = player.world.getBlockState(pos);
            Block block = state.getBlock();
            if (!player.world.isRemote
                    && !block.isAir(state, player.world, pos)
                    && state.getPlayerRelativeBlockHardness(player,player.world, pos) > 0
                    && block.canHarvestBlock(player.world, pos, player)){

                TileEntity tileEntity = player.world.getTileEntity(pos);
                int exp = ForgeHooks.onBlockBreakEvent(player.world, ((EntityPlayerMP) player).interactionManager.getGameType(), (EntityPlayerMP) player, pos);
                if (exp == -1) return;
                if (!player.capabilities.isCreativeMode && block.removedByPlayer(state, player.world, pos, player, true)){

                    if (filterMode(stack) && filterBlock.contains(block)){
                        player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
                        stack.damageItem(1, player);
                        continue;
                    }

                    block.harvestBlock(player.world, player, pos, state, tileEntity, stack);
                    block.dropXpOnBlockBreak(player.world, pos, exp);
                    stack.damageItem(1, player);
                }else {
                    player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }

        }
    }


    public boolean filterMode(ItemStack stack){
        if (!stack.hasTagCompound() || stack.getTagCompound() == null || !stack.getItem().equals(this)) return false;
        NBTTagCompound tagCompound = stack.getTagCompound();
        int mode = tagCompound.getInteger("filter");
        return mode == 1;
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        super.onCreated(stack, worldIn, playerIn);
        if (!stack.hasTagCompound() || stack.getTagCompound() == null || !stack.getTagCompound().hasKey("filter")){
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setInteger("filter", 0);
            stack.setTagCompound(tagCompound);
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!(stack.getItem() instanceof ItemBlastPickAxe)) return super.onItemRightClick(worldIn, playerIn, handIn);

        if (!stack.hasTagCompound() || stack.getTagCompound() == null || !stack.getTagCompound().hasKey("filter")){
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setInteger("filter", 0);
            stack.setTagCompound(tagCompound);
        }else {
            int mode = stack.getTagCompound().getInteger("filter");
            if (mode == 0){
                stack.getTagCompound().setInteger("filter", 1);
            }else{
                stack.getTagCompound().setInteger("filter", 0);
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        if (stack.getItem().equals(this) && stack.hasTagCompound() && stack.getTagCompound() != null && stack.getTagCompound().hasKey("filter")){
            String tip = filterMode(stack) ? "§2过滤模式开启" : "§4过滤模式关闭";
            tooltip.add(tip);
        }else {
            tooltip.add("右键启用过滤模式");
        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.equals(Enchantments.EFFICIENCY)
                || enchantment.equals(Enchantments.FORTUNE)
                || enchantment.equals(Enchantments.SILK_TOUCH);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

}
