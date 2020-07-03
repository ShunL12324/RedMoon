package com.github.ericliucn.redmoon.client.guis;

import codechicken.lib.gui.GuiDraw;
import codechicken.lib.gui.GuiScreenWidget;
import codechicken.lib.gui.IGuiActionListener;
import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.client.guis.cutomwidgets.GuiMyButton;
import com.github.ericliucn.redmoon.client.guis.cutomwidgets.GuiMyTextField;
import com.github.ericliucn.redmoon.items.ModItem;
import com.github.ericliucn.redmoon.network.bank.BalanceQueryMessage;
import com.github.ericliucn.redmoon.network.bank.TransactionMessage;
import com.github.ericliucn.redmoon.utils.InventoryHelper;
import com.github.ericliucn.redmoon.utils.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class BankGUI extends GuiScreenWidget implements IGuiActionListener {

    private static final ResourceLocation BACK_GROUND = new ResourceLocation("redmoon:textures/guis/back.png");
    public String balanceString;
    public double balance;
    private int amount = 0;
    private int freeInvSpace;
    private int energyStoneHold;
    private GuiMyTextField amountField;
    private GuiMyButton depositButton;
    private GuiMyButton withdrawButton;
    private EntityPlayerSP playerSP;
    private int mouseX;
    private int mouseY;

    public BankGUI(){
        this(248, 155);
    }

    public BankGUI(int xSize, int ySize){
        super(xSize, ySize);
    }

    @Override
    public void addWidgets() {
        depositButton = (GuiMyButton) new GuiMyButton(79, 110, 40, 20, "存入").setActionCommand("deposit");
        depositButton.setEnabled(false);
        withdrawButton = (GuiMyButton) new GuiMyButton(129, 110, 40, 20, "取出").setActionCommand("withdraw");
        withdrawButton.setEnabled(false);
        amountField = (GuiMyTextField) new GuiMyTextField(84, 70, 80, 20, "")
                .setAllowedCharacters("0123456789")
                .setMaxStringLength(9)
                .setActionCommand("setAmount");
        amountField.setFocused(true);
        this.add(amountField);
        this.add(depositButton);
        this.add(withdrawButton);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.refreshBalance();
        this.playerSP = Minecraft.getMinecraft().player;
    }

    @Override
    public void drawScreen(int mousex, int mousey, float f) {
        drawDefaultBackground();
        super.drawScreen(mousex, mousey, f);
        this.mouseX = mousex - guiLeft;
        this.mouseY = mousey - guiTop;
    }


    @Override
    public void drawBackground() {
        this.mc.renderEngine.bindTexture(BACK_GROUND);
        GlStateManager.color(1,1,1);
        drawTexturedModalRect(0, 0, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void drawForeground() {
        GlStateManager.pushMatrix();
        {
            GlStateManager.scale(2F, 2F, 2F);
            this.fontRenderer.drawString("当前余额", (this.xSize/2F - fontRenderer.getStringWidth("当前余额"))/2F, 10, 0xffffff, false);
            this.fontRenderer.drawString(this.balanceString, (this.xSize/2F - fontRenderer.getStringWidth(this.balanceString))/2F,22, 0xf5a402,false);
        }
        GlStateManager.popMatrix();
        refreshButtonStatus();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void keyTyped(char c, int keycode) throws IOException {
        super.keyTyped(c, keycode);
    }

    private void refreshFreeInvSpace(){
        this.freeInvSpace = InventoryHelper.getFreeSpace(playerSP.inventory, new ItemStack(ModItem.ITEM_ENERGY_STONE, 1, 0));
    }

    private void refreshStoneHold(){
        this.energyStoneHold = InventoryHelper.itemCount(playerSP, new ItemStack(ModItem.ITEM_ENERGY_STONE,1,0));
    }

    public void refreshButtonStatus(){
        refreshAmount();
        refreshStoneHold();
        refreshFreeInvSpace();

        if (this.amount == 0){
            this.withdrawButton.setEnabled(false);
            this.depositButton.setEnabled(false);
            if (withdrawButton.pointInside(mouseX, mouseY) || depositButton.pointInside(mouseX, mouseY)){
                GuiDraw.drawTip(mouseX, mouseY, "请先输入交易金额，只能为数字");
            }
            return;
        }

        if (this.amount > this.balance ){
            this.withdrawButton.setEnabled(false);
            if(this.withdrawButton.pointInside(this.mouseX, this.mouseY)) {
                GuiDraw.drawTip(mouseX, mouseY, "余额不足");
            }
        }else if (this.freeInvSpace < this.amount){
            this.withdrawButton.setEnabled(false);
            if (this.withdrawButton.pointInside(this.mouseX, this.mouseY)) {
                GuiDraw.drawTip(mouseX, mouseY, "背包空间不足");
            }
        }else {
            this.withdrawButton.setEnabled(true);
        }

        if (this.energyStoneHold < this.amount){
            this.depositButton.setEnabled(false);
            if (this.depositButton.pointInside(this.mouseX, this.mouseY)) {
                GuiDraw.drawTip(mouseX, mouseY, "晶石不足");
            }
        }else {
            this.depositButton.setEnabled(true);
        }


    }

    public void refreshBalance(){
        Main.NETWORK_WRAPPER.sendToServer(new BalanceQueryMessage("Coin"));
    }

    public void refreshAmount(){
        if (amountField.getText().isEmpty()){
            this.amount = 0;
            return;
        }
        this.amount = Integer.parseInt(amountField.getText());
    }

    @Override
    public void actionPerformed(String ident, Object... params) {
        switch (ident){
            case "setAmount":
                refreshAmount();
                break;
            case "withdraw":
                refreshAmount();
                if (this.amount <= 0) return;
                if (this.amount <= this.balance){
                    Main.NETWORK_WRAPPER.sendToServer(new TransactionMessage(Ref.WITHDRAW, this.amount, "Coin"));
                }
                break;
            case "deposit":
                refreshAmount();
                refreshStoneHold();
                if (this.energyStoneHold < this.amount) return;
                Main.NETWORK_WRAPPER.sendToServer(new TransactionMessage(Ref.DEPOSIT, this.amount, "Coin"));
                break;
            default:
                break;
        }
    }



}
