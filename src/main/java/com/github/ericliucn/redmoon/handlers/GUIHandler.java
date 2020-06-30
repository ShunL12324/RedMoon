package com.github.ericliucn.redmoon.handlers;

import com.github.ericliucn.redmoon.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public class GUIHandler implements IGuiHandler {

    public static final int BANK_GUI = 1;

    public GUIHandler(){
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.INSTANCE, this);
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case BANK_GUI:
                //return null;
            default:
                return null;
        }

    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case BANK_GUI:
                //return new BankGUI(248, 166);
                //return new AdminGeneratorGuiContainer(new AdminGeneratorContainer());
            default:
                return null;
        }
    }
}
