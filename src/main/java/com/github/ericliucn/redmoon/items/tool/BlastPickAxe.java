package com.github.ericliucn.redmoon.items.tool;

import com.github.ericliucn.redmoon.Main;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.EnumHelper;
import vazkii.arl.item.ItemModPickaxe;
import vazkii.botania.api.item.ISequentialBreaker;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.equipment.tool.elementium.ItemElementiumPick;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class BlastPickAxe extends ItemModPickaxe {

    private static final List<Material> MATERIALS = Arrays.asList(Material.ROCK, Material.IRON, Material.ICE, Material.GLASS, Material.PISTON, Material.ANVIL, Material.GRASS, Material.GROUND, Material.SAND, Material.SNOW, Material.CRAFTED_SNOW, Material.CLAY);


    public BlastPickAxe(String name, ToolMaterial material, String... variants) {
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
                    block.harvestBlock(player.world, player, pos, state, tileEntity, stack);
                    block.dropXpOnBlockBreak(player.world, pos, exp);
                }else {
                    player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }

        }
    }
}
