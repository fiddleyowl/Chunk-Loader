package com.philipzhan.chunkLoader.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.philipzhan.chunkLoader.SupportingFiles.PublicDefinitions.*;

public class ModBlocks {
    public static final Block CHUNK_LOADER = new Block(FabricBlockSettings
            .of(Material.PORTAL)
            .hardness(50.0f)
            .resistance(5000.0f)
            .breakByHand(false)
            .breakByTool(FabricToolTags.PICKAXES, 3)
            .requiresTool()
            .sounds(BlockSoundGroup.STONE)
            .luminance(15));

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(modId, "chunk_loader"), CHUNK_LOADER);
    }
}
