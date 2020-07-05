package com.github.ericliucn.redmoon.blocks;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.blocks.tiles.TileEnergyStoneUpdater;
import com.github.ericliucn.redmoon.handlers.GUIHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import vazkii.arl.block.BlockMod;
import vazkii.arl.block.BlockModContainer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockEnergyUpdater extends BlockModContainer {

    private static final IProperty<EnumFacing> FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockEnergyUpdater(String name, Material materialIn, String... variants) {
        super(name, materialIn, variants);
        this.setCreativeTab(Main.creativeTab);
        this.setHardness(5F);
        this.setHarvestLevel("pickaxe", 2);
        this.setResistance(50F);
        this.setSoundType(SoundType.METAL);
        this.setLightLevel(15F);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public String getModNamespace() {
        return Main.MOD_ID;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        playerIn.openGui(Main.INSTANCE, GUIHandler.ENERGY_STONE_UPDATER, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }


    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new TileEnergyStoneUpdater();
    }

    @Override
    public void breakBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);

        Capability<IItemHandler> capability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

        IItemHandler up = tileEntity.getCapability(capability, EnumFacing.UP);
        IItemHandler down = tileEntity.getCapability(capability, EnumFacing.DOWN);
        IItemHandler side = tileEntity.getCapability(capability, EnumFacing.NORTH);

        Block.spawnAsEntity(worldIn, pos, up.getStackInSlot(0));
        Block.spawnAsEntity(worldIn, pos, down.getStackInSlot(0));
        Block.spawnAsEntity(worldIn, pos, side.getStackInSlot(0));
        Block.spawnAsEntity(worldIn, pos, side.getStackInSlot(1));
        Block.spawnAsEntity(worldIn, pos, side.getStackInSlot(2));
        Block.spawnAsEntity(worldIn, pos, side.getStackInSlot(3));

        super.breakBlock(worldIn, pos, state);
    }
}
