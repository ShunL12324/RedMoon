package com.github.ericliucn.redmoon.client.guis;

import codechicken.lib.inventory.container.GuiContainerWidget;
import com.github.ericliucn.redmoon.blocks.containers.ContainerUpdater;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.text.NumberFormat;

public class GUIContainerUpdater extends GuiContainerWidget {

    private static final ResourceLocation guiTex = new ResourceLocation("redmoon:textures/guis/energy_stone_updater.png");
    private final String name = I18n.format("tile.redmoon:energy_stone_updater.name");


    public GUIContainerUpdater(Container inventorySlots) {
        super(inventorySlots, 176, 167);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    public void drawBackground() {
        super.drawBackground();
        //bind texture
        this.mc.renderEngine.bindTexture(guiTex);
        //draw background
        drawTexturedModalRect(0, 0, 0, 0, this.xSize, this.ySize);

        //get container instance, and get progress and stream
        ContainerUpdater container = ((ContainerUpdater) this.inventorySlots);
        double progress = Math.min(container.getProgress()/10000D, 1D);

        //draw progressBar
        drawTexturedModalRect(88,36,176,0, Math.min((int) (28 * progress), 28), 16);

        //draw tips
        this.fontRenderer.drawString("输入： "+ container.getStream() + " EU/t", 92,62, 0x4a4a4a, false);
        //format percentage
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(0);
        this.fontRenderer.drawString(format.format(progress), 92,30, 0x4a4a4a, false);

        //draw machine name
        this.fontRenderer.drawString(name, 95, 10, 0x4a4a4a,false);
    }
}
