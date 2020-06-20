package com.github.ericliucn.redmoon.handlers;

import com.github.ericliucn.redmoon.items.IHasModel;
import com.github.ericliucn.redmoon.items.ModItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {

    public EventHandler(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerItem(RegistryEvent.Register<Item> event){
        for (Item item: ModItem.ITEMS
             ) {
            event.getRegistry().register(item);
            if (item instanceof IHasModel){
                ((IHasModel) item).registerModel(item, ((IHasModel) item).getName(item), 0);
            }
        }
    }

}
