package com.philipzhan.chunkLoader;

import com.philipzhan.chunkLoader.registry.ModBlocks;
import com.philipzhan.chunkLoader.registry.ModItems;
import net.fabricmc.api.ModInitializer;

public class ChunkLoader implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        ModItems.registerItems();
        ModBlocks.registerBlocks();

        System.out.println("Hello Fabric world!");
    }
}
