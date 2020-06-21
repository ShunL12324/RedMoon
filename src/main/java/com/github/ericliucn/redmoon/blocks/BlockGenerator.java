package com.github.ericliucn.redmoon.blocks;

import com.github.ericliucn.redmoon.Main;
import net.minecraft.block.material.Material;
import vazkii.arl.block.BlockMod;

public class BlockGenerator extends BlockMod {

    public BlockGenerator(String name, Material materialIn, String... variants) {
        super(name, materialIn, variants);
        this.setCreativeTab(Main.creativeTab);
    }

    @Override
    public String getModNamespace() {
        return Main.MOD_ID;
    }

    @Override
    public String getUniqueModel() {
        return "block_generator";
    }
}
