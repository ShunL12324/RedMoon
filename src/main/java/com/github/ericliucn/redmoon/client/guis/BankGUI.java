package com.github.ericliucn.redmoon.client.guis;

import codechicken.lib.gui.GuiCCButton;
import codechicken.lib.gui.GuiCCTextField;
import codechicken.lib.gui.GuiScreenWidget;
import codechicken.lib.gui.IGuiActionListener;
import com.github.ericliucn.redmoon.Main;
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

    private static final ResourceLocation BACK_GROUND = new ResourceLocation("redmoon:textures/guis/background.png");
    public String balanceString;
    public double balance;
    private static final String currentBal = "当前余额";
    private static final String inputTip = "在上方输入你要存取的数额，只能输数字，回车确认";
    private int amount = 0;
    private int freeInvSpace;
    private int energyStoneHold;
    private GuiCCTextField amountField;
    private GuiCCButton depositButton;
    private GuiCCButton withdrawButton;
    private EntityPlayerSP playerSP;
    private int mouseX;
    private int mouseY;

    public BankGUI(){
        this(248, 166);
    }

    public BankGUI(int xSize, int ySize){
        super(xSize, ySize);
    }

    @Override
    public void addWidgets() {
        depositButton = new GuiCCButton(79, 120, 40, 20, "存入").setActionCommand("deposit");
        depositButton.setEnabled(false);
        withdrawButton = new GuiCCButton(129, 120, 40, 20, "取出").setActionCommand("withdraw");
        withdrawButton.setEnabled(false);
        amountField = new GuiCCTextField(84, 70, 80, 20, "")
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
        drawTexturedModalRect(0, 0, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void drawForeground() {
        GlStateManager.pushMatrix();
        {
            GlStateManager.scale(2F, 2F, 2F);
            this.fontRenderer.drawString(currentBal, (this.xSize/2F - fontRenderer.getStringWidth(currentBal))/2F, 10, 0x454445, false);
            this.fontRenderer.drawString(this.balanceString, (this.xSize/2F - fontRenderer.getStringWidth(this.balanceString))/2F,22, 0x614a00,false);
        }
        GlStateManager.popMatrix();
        this.fontRenderer.drawString(inputTip, (this.xSize - fontRenderer.getStringWidth(inputTip))/2F, 100, 0x454445, false);
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
            fontRenderer.drawString("请在上方输入交易数额", (this.xSize - fontRenderer.getStringWidth("请在上方输入交易数额"))/2F, 150F, 0x990516, false);
            return;
        }

        if (this.amount > this.balance ){
            this.withdrawButton.setEnabled(false);
            if(this.withdrawButton.pointInside(this.mouseX, this.mouseY)) {
                fontRenderer.drawString("余额不足", this.mouseX + 5, this.mouseY, 0x990516, false);
            }
        }else if (this.freeInvSpace < this.amount){
            this.withdrawButton.setEnabled(false);
            if (this.withdrawButton.pointInside(this.mouseX, this.mouseY)) {
                fontRenderer.drawString("背包空间不足", this.mouseX + 5, this.mouseY, 0x990516, false);
            }
        }else {
            this.withdrawButton.setEnabled(true);
        }

        if (this.energyStoneHold < this.amount){
            this.depositButton.setEnabled(false);
            if (this.depositButton.pointInside(this.mouseX, this.mouseY)) {
                fontRenderer.drawString("晶石不足", mouseX + 5, mouseY, 0x990516, false);
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
            case "deposit":
                refreshAmount();
                refreshStoneHold();
                if (this.energyStoneHold < this.amount) return;
                Main.NETWORK_WRAPPER.sendToServer(new TransactionMessage(Ref.DEPOSIT, this.amount, "Coin"));
        }
    }



}
