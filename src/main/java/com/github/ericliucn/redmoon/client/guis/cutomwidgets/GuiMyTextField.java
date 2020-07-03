package com.github.ericliucn.redmoon.client.guis.cutomwidgets;

import codechicken.lib.gui.GuiCCTextField;

public class GuiMyTextField extends GuiCCTextField {

    public GuiMyTextField(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }

    @Override
    public int getTextColour() {
        return this.isEnabled() ? 0xe0e0e0 : 0x707070;
    }


    @Override
    public void drawBackground() {
        drawRect(x - 1, y - 1, x + width + 1, y + height + 1, 0xffa0a0a0);
        drawRect(x, y, x + width, y + height, 0xff000000);
    }




}
