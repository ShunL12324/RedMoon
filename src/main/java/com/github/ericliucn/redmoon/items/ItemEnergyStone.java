package com.github.ericliucn.redmoon.items;

import com.github.ericliucn.redmoon.Main;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.arl.item.ItemMod;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.item.IManaDissolvable;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.common.core.handler.ModSounds;
import vazkii.botania.common.network.PacketBotaniaEffect;
import vazkii.botania.common.network.PacketHandler;

import javax.annotation.Nullable;
import java.util.List;

public class ItemEnergyStone extends ItemMod implements IManaDissolvable {

    private final int mana_common = 5000;
    private final int mana_good = 10000;
    private final int mana_excellent = 50000;
    private final int mana_epic = 500000;
    private final int mana_legend = 1000000;

    public ItemEnergyStone(String name, String... variants) {
        super(name, variants);
        this.setCreativeTab(Main.creativeTab);
        this.setMaxStackSize(16);
    }

    @Override
    public String getModNamespace() {
        return Main.MOD_ID;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }



    @Override
    public void onDissolveTick(IManaPool pool, ItemStack stack, EntityItem item) {

        if (pool.isFull() || pool.getCurrentMana() == 0)
            return;

        int meta = stack.getMetadata();
        if (!item.world.isRemote){
            TileEntity tile = (TileEntity)pool;
            switch (meta){
                case 0:
                    pool.recieveMana(mana_common);
                    break;
                case 1:
                    pool.recieveMana(mana_good);
                    break;
                case 2:
                    pool.recieveMana(mana_excellent);
                    break;
                case 3:
                    pool.recieveMana(mana_epic);
                    break;
                case 4:
                    pool.recieveMana(mana_legend);
                    break;
            }
            stack.shrink(1);
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(item.world, tile.getPos());
            PacketHandler.sendToNearby(item.world, item, new PacketBotaniaEffect(PacketBotaniaEffect.EffectType.BLACK_LOTUS_DISSOLVE, item.posX, tile.getPos().getY() + 0.5, item.posZ));
        }

        switch (meta){
            case 0:
                item.playSound(ModSounds.blackLotus, 0.5F, 0.1F);
                break;
            case 1:
                item.playSound(ModSounds.blackLotus, 0.5F, 0.3F);
                break;
            case 2:
                item.playSound(ModSounds.blackLotus, 0.5F, 0.5F);
                break;
            case 3:
                item.playSound(ModSounds.blackLotus, 0.5F, 0.8F);
                break;
            case 4:
                item.playSound(ModSounds.blackLotus, 0.5F, 1F);
                break;
        }
    }


}
