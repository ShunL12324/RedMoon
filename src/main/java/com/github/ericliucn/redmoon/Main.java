package com.github.ericliucn.redmoon;

import com.github.ericliucn.redmoon.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.slf4j.Logger;

@Mod(
        modid = Main.MOD_ID,
        name = Main.MOD_NAME,
        version = Main.VERSION,
        dependencies = "required-after:ic2@[2.8.218,)"
)
public class Main {

    public static final String MOD_ID = "redmoon";
    public static final String MOD_NAME = "RedMoon";
    public static final String VERSION = "1.0";

    //网络包
    public static final SimpleNetworkWrapper NETWORK_WRAPPER = new SimpleNetworkWrapper(Main.MOD_NAME);


    // 模组的实例
    @Mod.Instance(MOD_ID)
    public static Main INSTANCE;

    // 模组的Proxy，用于在客户端或者服务端单方面调用代码
    @SidedProxy(clientSide = "com.github.ericliucn.redmoon.proxy.ClientProxy", serverSide = "com.github.ericliucn.redmoon.proxy.ServerProxy", modId = MOD_ID)
    public static CommonProxy PROXY;

    public static RMCreativeTab creativeTab = new RMCreativeTab("RedMoon");

    @Mod.EventHandler
    public void start(FMLConstructionEvent event) {
        PROXY.start(event);
    }


    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        PROXY.preinit(event);
    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.init(event);
    }


    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        PROXY.postinit(event);
    }

    @Mod.EventHandler
    public void serverstarting(FMLServerStartingEvent event){
        PROXY.serverstarting(event);
    }

}
