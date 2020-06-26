package com.github.ericliucn.redmoon.handlers;

import com.github.ericliucn.redmoon.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public class GUIHandler implements IGuiHandler {

    public static final int ADMIN_GENERATOR = 1;

    public GUIHandler(){
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.INSTANCE, this);
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case ADMIN_GENERATOR:
                //return new AdminGeneratorContainer();
            default:
                return null;
        }

    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case ADMIN_GENERATOR:
                //return new AdminGeneratorGuiContainer(new AdminGeneratorContainer());
            default:
                return null;
        }
    }
}
