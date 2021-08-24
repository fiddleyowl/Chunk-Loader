package com.philipzhan.chunkLoader;

import com.philipzhan.chunkLoader.registry.ModBlocks;
import com.philipzhan.chunkLoader.registry.ModItems;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import net.minecraft.world.chunk.WorldChunk;

import java.util.Arrays;

import static com.philipzhan.chunkLoader.SupportingFiles.PublicDefinitions.*;

public class ChunkLoader implements ModInitializer {


    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        ModItems.registerItems();
        ModBlocks.registerBlocks();

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            LongSet longSet = server.getOverworld().getForcedChunks();
            for (Long longItem: longSet) {
                String numbers = Long.toHexString(longItem);
                String numberZ = numbers.substring(0,8);
                long longZ = Long.parseLong(numberZ, 16);
                int z = (int) longZ;
                String numberX = numbers.substring(8,16);
                long longX = Long.parseLong(numberX, 16);
                int x = (int) longX;
                chunkXs.add(x);
                chunkZs.add(z);
            }
            System.out.println("SERVER_STARTED");
            System.out.println(Arrays.toString(chunkXs.toArray()));
            System.out.println(Arrays.toString(chunkZs.toArray()));
        });

        ServerTickEvents.START_SERVER_TICK.register(server -> {
            ServerWorld serverWorld = server.getOverworld();
            int tickSpeed = serverWorld.getGameRules().getInt(GameRules.RANDOM_TICK_SPEED);
            outerFor:
            for (int i = 0; i < chunkXs.size(); i++) {
                WorldChunk worldChunk = serverWorld.getChunk(chunkXs.get(i), chunkZs.get(i));
                for (ServerPlayerEntity player: serverWorld.getPlayers()) {
                    if (shouldTickChunk(worldChunk.getPos(), player.getPos())) {
                        serverWorld.tickChunk(worldChunk, tickSpeed);
                        System.out.println("Ticked chunk x: " + worldChunk.getPos().x + ", z: " + worldChunk.getPos().z);
                        continue outerFor;
                    }
                }
            }
        });

    }
}
