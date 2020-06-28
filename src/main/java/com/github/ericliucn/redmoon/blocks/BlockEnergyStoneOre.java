package com.github.ericliucn.redmoon.blocks;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.items.ModItem;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.arl.block.BlockMod;
import vazkii.arl.interf.IModBlock;

import java.util.List;
import java.util.Random;

public class BlockEnergyStoneOre extends BlockMod {

    public BlockEnergyStoneOre(String name, Material materialIn, String... variants) {
        super(name, materialIn, variants);
        this.setHardness(5F);
        this.setCreativeTab(Main.creativeTab);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(10F);
        this.setResistance(20);
        this.setSoundType(SoundType.STONE);
    }

    @Override
    public String getModNamespace() {
        return Main.MOD_ID;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItem.ITEM_ENERGY_STONE;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return this.quantityDropped(random) + random.nextInt(fortune + 1);
    }

    @Override
    public int quantityDropped(Random random) {
        return super.quantityDropped(random);
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosionIn) {
        return false;
    }

}
