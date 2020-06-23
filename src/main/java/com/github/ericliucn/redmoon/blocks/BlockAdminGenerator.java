package com.github.ericliucn.redmoon.blocks;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.blocks.tiles.TileAdminGenerator;
import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityBlock;
import ic2.core.ref.TeBlock.DefaultDrop;
import ic2.core.ref.TeBlock.HarvestTool;
import ic2.core.ref.TeBlock.ITePlaceHandler;
import ic2.core.util.Util;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Set;

public enum BlockAdminGenerator implements ITeBlock {

    admin_generator(TileAdminGenerator.class, 0, false, Util.noFacings, true, HarvestTool.Pickaxe, DefaultDrop.Machine, 5, 10, EnumRarity.RARE, Material.IRON, false);

    private final Class<? extends TileEntityBlock> teClass;
    private final int ID;
    private final boolean hasActive;
    private final Set<EnumFacing> possibleFacings;
    private final boolean canBeWrenched;
    private final HarvestTool tool;
    private final DefaultDrop drop;
    private final float hardness;
    private final float explosionResistance;
    private final EnumRarity rarity;
    private final Material material;
    private final boolean isTransparent;
    private TileEntityBlock dummyTe;
    private ITePlaceHandler placeHandler;

    public static final ResourceLocation IDENTITY = new ResourceLocation(Main.MOD_NAME, "machines");

    BlockAdminGenerator(Class<? extends TileEntityBlock> teClass, int id, boolean hasActive, Set<EnumFacing> possibleFacings, boolean canBeWrenched, HarvestTool tool, DefaultDrop drop, float hardness, float explosionResistance, EnumRarity rarity, Material material, boolean isTransparent) {
        this.teClass = teClass;
        this.ID = id;
        this.hasActive = hasActive;
        this.possibleFacings = possibleFacings;
        this.canBeWrenched = canBeWrenched;
        this.tool = tool;
        this.drop = drop;
        this.hardness = hardness;
        this.explosionResistance = explosionResistance;
        this.rarity = rarity;
        this.material = material;
        this.isTransparent = isTransparent;

        TileEntity.register(Main.MOD_ID + ':' + getName(), TileAdminGenerator.class);
    }

    @Override
    public ResourceLocation getIdentifier() {
        return IDENTITY;
    }

    @Override
    public boolean hasItem() {
        return false;
    }

    @Nullable
    @Override
    public Class<? extends TileEntityBlock> getTeClass() {
        return teClass;
    }

    @Override
    public boolean hasActive() {
        return false;
    }

    @Override
    public Set<EnumFacing> getSupportedFacings() {
        return possibleFacings;
    }

    @Override
    public float getHardness() {
        return hardness;
    }

    @Override
    public float getExplosionResistance() {
        return explosionResistance;
    }

    @Override
    public HarvestTool getHarvestTool() {
        return null;
    }

    @Override
    public DefaultDrop getDefaultDrop() {
        return null;
    }

    @Override
    public EnumRarity getRarity() {
        return null;
    }

    @Override
    public boolean allowWrenchRotating() {
        return false;
    }

    @Nullable
    @Override
    public TileEntityBlock getDummyTe() {
        return dummyTe;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    public static void buildDummies() {
 		for (BlockAdminGenerator block : values()) {
 			//System.out.printf("Building %s (with teClass %s)%n", block.getName(), block.teClass);
 			if (block.teClass != null) {
 				try {
 					block.dummyTe = block.teClass.newInstance();
 				} catch (Exception e) {
 					e.printStackTrace();
 				}
 			}
 		}
 	}
}
