package com.github.ericliucn.redmoon.proxy;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.client.guis.BankGUI;
import com.github.ericliucn.redmoon.utils.GUIIDS;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.*;

public class ClientProxy extends CommonProxy{

    @Override
    public void start(FMLConstructionEvent event) {
        super.start(event);
    }

    @Override
    public void preinit(FMLPreInitializationEvent event) {
        OBJLoader.INSTANCE.addDomain(Main.MOD_ID);
        super.preinit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postinit(FMLPostInitializationEvent event) {
        super.postinit(event);
    }

    @Override
    public void serverstarting(FMLServerStartingEvent event) {
        super.serverstarting(event);
    }

    @Override
    public void displayGUI(int ID) {
        switch (ID){
            case GUIIDS.BANK_GUI:
                Minecraft.getMinecraft().displayGuiScreen(new BankGUI());
        }
    }
}
