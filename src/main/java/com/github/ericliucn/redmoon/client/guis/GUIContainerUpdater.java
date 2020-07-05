package com.github.ericliucn.redmoon.client.guis;

import codechicken.lib.inventory.container.GuiContainerWidget;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GUIContainerUpdater extends GuiContainerWidget {

    private static final ResourceLocation guiTex = new ResourceLocation("redmoon:textures/guis/energy_stone_updater.png");


    public GUIContainerUpdater(Container inventorySlots) {
        super(inventorySlots, 176, 196);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void drawBackground() {
        super.drawBackground();
        this.mc.renderEngine.bindTexture(guiTex);
        drawTexturedModalRect(0, 0, 0, 0, this.xSize, this.ySize);
    }
}
