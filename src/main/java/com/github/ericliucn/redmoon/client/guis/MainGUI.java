package com.github.ericliucn.redmoon.client.guis;

import codechicken.lib.gui.GuiDraw;
import codechicken.lib.gui.GuiScreenWidget;
import com.github.ericliucn.redmoon.client.guis.cutomwidgets.GuiMyButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class MainGUI extends GuiScreenWidget {

    private int mouseX;
    private int mouseY;
    private GuiMyButton teleport;
    private GuiMyButton shop;
    private GuiMyButton voucher_shop;
    private GuiMyButton home;
    private GuiMyButton plot;
    private GuiMyButton ore_world;

    private static final ResourceLocation TEX = new ResourceLocation("redmoon:textures/guis/main_gui_back.png");

    public MainGUI(){
        super(248, 176);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void addWidgets() {
        super.addWidgets();
    }

    @Override
    public void drawScreen(int mousex, int mousey, float f) {
        this.mouseX = mousex;
        this.mouseY = mousey;
        drawDefaultBackground();
        super.drawScreen(mousex, mousey, f);
    }

    @Override
    public void drawBackground() {
        super.drawBackground();
        mc.renderEngine.bindTexture(TEX);
        drawTexturedModalRect(0, 0, 0, 0, xSize, ySize);
        this.drawIcons();
    }

    private void drawIcons(){
        ItemStack tp = new ItemStack(Items.ENDER_PEARL);
        ItemStack shop = new ItemStack(Items.GOLD_INGOT);
        ItemStack voucher_shop = new ItemStack(Items.NETHER_STAR);
        ItemStack home= new ItemStack(Items.BED);
        ItemStack plot= new ItemStack(Items.OAK_DOOR);
        ItemStack ore_world = new ItemStack(Items.EMERALD);

        RenderHelper.enableGUIStandardItemLighting();

        GlStateManager.pushMatrix();
        {
            double x = 1.5;
            double y = 1.5;
            double z = 1.5;
            GlStateManager.scale(x, y, z);
            this.itemRender.renderItemIntoGUI(tp, (int) (30/x), (int) (54/y));
            this.itemRender.renderItemIntoGUI(shop, (int) (90/x), (int) (54/y));
            this.itemRender.renderItemIntoGUI(voucher_shop, (int) (150/x), (int) (54/y));
            this.itemRender.renderItemIntoGUI(home, (int) (210/x), (int) (54/y));
            this.itemRender.renderItemIntoGUI(plot, (int) (30/x), (int) (116/y));
            this.itemRender.renderItemIntoGUI(ore_world, (int) (90/x), (int) (116/y));
        }
        GlStateManager.popMatrix();

    }

    @Override
    public void drawForeground() {
        super.drawForeground();
    }

    @Override
    public void actionPerformed(String ident, Object... params) {
        super.actionPerformed(ident, params);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
