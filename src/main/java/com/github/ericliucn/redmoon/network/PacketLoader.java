package com.github.ericliucn.redmoon.network;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.network.bank.BalanceQueryBackMessage;
import com.github.ericliucn.redmoon.network.bank.BalanceQueryMessage;
import net.minecraftforge.fml.relauncher.Side;

public class PacketLoader {

    private static int id = 1;

    public PacketLoader(){
        Main.NETWORK_WRAPPER.registerMessage(CommandMessage.CommandMessageHandler.class, CommandMessage.class, id++, Side.SERVER);
        Main.NETWORK_WRAPPER.registerMessage(BalanceQueryMessage.BalanceQueryMessageHandler.class, BalanceQueryMessage.class, id++, Side.SERVER);
        Main.NETWORK_WRAPPER.registerMessage(BalanceQueryBackMessage.BalanceQueryBackMessageHandler.class, BalanceQueryBackMessage.class, id++, Side.CLIENT);
        Main.NETWORK_WRAPPER.registerMessage(GUIOpenMessage.GUIOpenMessageHandler.class, GUIOpenMessage.class, id++, Side.CLIENT);
    }
}
