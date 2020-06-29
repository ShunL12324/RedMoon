package com.github.ericliucn.redmoon.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class NetUtil {

    public static ByteBuf getCommandBuf(String cmd){
        ByteBuf byteBuf = Unpooled.buffer();
        ByteBufUtils.writeUTF8String(byteBuf, cmd);
        return byteBuf;
    }
}
