package com.github.ericliucn.redmoon.handlers;

import com.github.ericliucn.redmoon.blocks.BlockAdminGenerator;
import ic2.api.event.TeBlockFinalCallEvent;
import ic2.core.block.TeBlockRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TeHandler {

    public TeHandler(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void teBlockFinalCall(TeBlockFinalCallEvent event){
        TeBlockRegistry.addAll(BlockAdminGenerator.class, BlockAdminGenerator.IDENTITY);
        MinecraftForge.EVENT_BUS.unregister(this);
    }
}
