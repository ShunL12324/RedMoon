package com.github.ericliucn.redmoon.handlers;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.blocks.containers.ContainerUpdater;
import com.github.ericliucn.redmoon.client.guis.GUIContainerUpdater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public class GUIHandler implements IGuiHandler {

    public static final int ENERGY_STONE_UPDATER = 1;

    public GUIHandler(){
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.INSTANCE, this);
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case ENERGY_STONE_UPDATER:
                return new ContainerUpdater(player, world, new BlockPos(x,y,z));
            default:
                return null;
        }

    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case ENERGY_STONE_UPDATER:
                return new GUIContainerUpdater(new ContainerUpdater(player, world, new BlockPos(x,y,z)));
            default:
                return null;
        }
    }
}
