package com.github.ericliucn.redmoon.client.guis.mywidgets;

import codechicken.lib.gui.GuiCCTextField;

public class GuiMyTextField extends GuiCCTextField {

    public GuiMyTextField(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }

    @Override
    public void keyTyped(char c, int keycode) {
        super.keyTyped(c, keycode);
    }
}
