package com.github.ericliucn.redmoon.handlers;

import com.github.ericliucn.redmoon.blocks.TEMachines;
import ic2.api.event.TeBlockFinalCallEvent;
import ic2.core.block.TeBlockRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TEHandler {

    public TEHandler(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void teBlockFinalCall(TeBlockFinalCallEvent event){
        TeBlockRegistry.addAll(TEMachines.class, TEMachines.IDENTITY);
        MinecraftForge.EVENT_BUS.unregister(this);
    }
}
