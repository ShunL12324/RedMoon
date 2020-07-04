package com.github.ericliucn.redmoon.blocks;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.blocks.tiles.TileAdminGenerator;
import com.github.ericliucn.redmoon.blocks.tiles.TileEnergyStoneUpdater;
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

public enum TEMachines implements ITeBlock  {

    energy_stone_updater(TileEnergyStoneUpdater.class, 1, false),
    admin_generator(TileAdminGenerator.class, 0, false);

    private final Class<? extends TileEntityBlock> teClass;
    private final int ID;
    private final boolean hasActive;
    private TileEntityBlock dummyTe;
    private ITePlaceHandler placeHandler;

    public static final ResourceLocation IDENTITY = new ResourceLocation(Main.MOD_NAME, "machines");

    TEMachines(Class<? extends TileEntityBlock> teClass, int id, boolean hasActive) {
        this.teClass = teClass;
        this.ID = id;
        this.hasActive = hasActive;

        TileEntity.register(Main.MOD_ID + ':' + getName(), this.teClass);
    }

    @Override
    public ResourceLocation getIdentifier() {
        return IDENTITY;
    }

    @Override
    public boolean hasItem() {
        return true;
    }

    @Nullable
    @Override
    public Class<? extends TileEntityBlock> getTeClass() {
        return this.teClass;
    }

    @Override
    public boolean hasActive() {
        return this.hasActive;
    }

    @Override
    public Set<EnumFacing> getSupportedFacings() {
        return Util.horizontalFacings;
    }

    @Override
    public float getHardness() {
        return 5F;
    }

    @Override
    public float getExplosionResistance() {
        return 15F;
    }

    @Override
    public HarvestTool getHarvestTool() {
        return HarvestTool.Pickaxe;
    }

    @Override
    public DefaultDrop getDefaultDrop() {
        return DefaultDrop.Machine;
    }

    @Override
    public EnumRarity getRarity() {
        return EnumRarity.RARE;
    }

    @Override
    public boolean allowWrenchRotating() {
        return true;
    }

    @Nullable
    @Override
    public TileEntityBlock getDummyTe() {
        return this.dummyTe;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public int getId() {
        return this.ID;
    }

    @Override
    public boolean isTransparent() {
        return false;
    }

    @Override
    public Material getMaterial() {
        return Material.IRON;
    }


    public static void buildDummies() {
 		for (TEMachines block : values()) {
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
