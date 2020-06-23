package com.github.ericliucn.redmoon.proxy;

import com.github.ericliucn.redmoon.blocks.BlockAdminGenerator;
import com.github.ericliucn.redmoon.blocks.ModBlock;
import com.github.ericliucn.redmoon.blocks.tiles.ModTile;
import com.github.ericliucn.redmoon.command.TestCommand;
import com.github.ericliucn.redmoon.handlers.EventHandler;
import com.github.ericliucn.redmoon.handlers.TeHandler;
import com.github.ericliucn.redmoon.items.ModItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.*;

public class CommonProxy {

    public void start(FMLConstructionEvent event){
        new TeHandler();
    }

    public void preinit(FMLPreInitializationEvent event){
        new EventHandler();
        ModItem.preInit();
        ModBlock.preInit();
        ModTile.preInit();
    }

    public void init(FMLInitializationEvent event){
        BlockAdminGenerator.buildDummies();
    }

    public void postinit(FMLPostInitializationEvent event){

    }

    public void serverstarting(FMLServerStartingEvent event){
        // 注册指令
        event.registerServerCommand(new TestCommand());
    }


}
