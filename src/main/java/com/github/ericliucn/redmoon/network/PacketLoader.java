package com.github.ericliucn.redmoon.network;

import com.github.ericliucn.redmoon.Main;
import net.minecraftforge.fml.relauncher.Side;

public class PacketLoader {

    private static int id = 1;

    public PacketLoader(){
        Main.NETWORK_WRAPPER.registerMessage(CommandMessage.CommandMessageHandler.class, CommandMessage.class, id++, Side.SERVER);
    }
}
