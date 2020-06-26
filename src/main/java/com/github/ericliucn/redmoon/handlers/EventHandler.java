package com.github.ericliucn.redmoon.handlers;

import net.minecraftforge.common.MinecraftForge;

public class EventHandler {

    public EventHandler(){
        MinecraftForge.EVENT_BUS.register(this);
    }


}
