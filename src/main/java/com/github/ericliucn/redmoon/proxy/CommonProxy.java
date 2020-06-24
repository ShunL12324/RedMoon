package com.github.ericliucn.redmoon.proxy;

import com.github.ericliucn.redmoon.blocks.TEMachines;
import com.github.ericliucn.redmoon.command.TestCommand;
import com.github.ericliucn.redmoon.handlers.EventHandler;
import com.github.ericliucn.redmoon.handlers.TEHandler;
import com.github.ericliucn.redmoon.items.ModItem;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy {

    public void start(FMLConstructionEvent event){
        new TEHandler();
    }

    public void preinit(FMLPreInitializationEvent event){
        new EventHandler();
        ModItem.preInit();
    }

    public void init(FMLInitializationEvent event){
        TEMachines.buildDummies();
    }

    public void postinit(FMLPostInitializationEvent event){

    }

    public void serverstarting(FMLServerStartingEvent event){
        // 注册指令
        event.registerServerCommand(new TestCommand());
    }


}
