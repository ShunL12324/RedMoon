package com.github.ericliucn.redmoon.client.guis.cutomwidgets;

import codechicken.lib.gui.GuiCCButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiMyButton extends GuiCCButton {


    public static final ResourceLocation guiTex = new ResourceLocation("redmoon:textures/guis/widget.png");

    public GuiMyButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }

    @Override
    public void drawButtonTex(int mousex, int mousey) {
        GlStateManager.color(1, 1, 1, 1);
        renderEngine.bindTexture(guiTex);
        int state = getButtonTex(mousex, mousey);
        drawTexturedModalRect(x, y, 0, 46 + state * 20, width / 2, height / 2);//top left
        drawTexturedModalRect(x + width / 2, y, 200 - width / 2, 46 + state * 20, width / 2, height / 2);//top right
        drawTexturedModalRect(x, y + height / 2, 0, 46 + state * 20 + 20 - height / 2, width / 2, height / 2);//bottom left
        drawTexturedModalRect(x + width / 2, y + height / 2, 200 - width / 2, 46 + state * 20 + 20 - height / 2, width / 2, height / 2);//bottom right
    }
}
