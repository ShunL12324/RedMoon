package com.github.ericliucn.redmoon.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class ServerProxy extends CommonProxy{

    @Override
    public void preinit(FMLPreInitializationEvent event) {
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
}
