package com.github.ericliucn.redmoon.client.guis;

import codechicken.lib.gui.GuiCCButton;
import codechicken.lib.gui.GuiCCTextField;
import codechicken.lib.gui.GuiScreenWidget;
import codechicken.lib.gui.IGuiActionListener;
import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.network.bank.BalanceQueryMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class BankGUI extends GuiScreenWidget implements IGuiActionListener {

    private static final ResourceLocation BACK_GROUND = new ResourceLocation("redmoon:textures/guis/background.png");
    public double balance;

    public BankGUI(){
        this(248, 166);
    }

    public BankGUI(int xSize, int ySize){
        super(xSize, ySize);
    }

    @Override
    public void addWidgets() {
        this.add(new GuiCCButton(10, 20, 40, 20, "存入").setActionCommand("1"));
        this.add(new GuiCCButton(20, 20, 40, 20, "取出").setActionCommand("2"));
        this.add(new GuiCCTextField(20, 30, 20, 10, "金额"));
    }

    @Override
    public void initGui() {
        super.initGui();
        setBalance();
    }

    @Override
    public void drawScreen(int mousex, int mousey, float f) {
        drawDefaultBackground();
        super.drawScreen(mousex, mousey, f);
    }


    @Override
    public void drawBackground() {
        this.mc.renderEngine.bindTexture(BACK_GROUND);
        drawTexturedModalRect(0, 0, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void drawForeground() {
        this.fontRenderer.drawString(String.valueOf(this.balance), 0,0,0,false);
        if (Mouse.isGrabbed()) {
            Mouse.setGrabbed(false);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private void setBalance(){
        Main.NETWORK_WRAPPER.sendToServer(new BalanceQueryMessage("Coin"));
    }

    @Override
    public void actionPerformed(String ident, Object... params) {
        if (ident.equals("1")){
            System.out.println("存入");
        }else if (ident.equals("2")){
            System.out.println("取出");
        }
    }
}
