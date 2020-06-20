package com.github.ericliucn.redmoon.handlers;

import com.github.ericliucn.redmoon.items.ModItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.arl.interf.IVariantHolder;
import vazkii.arl.util.ModelHandler;

public class EventHandler {

    public EventHandler(){
        MinecraftForge.EVENT_BUS.register(this);
    }


}
