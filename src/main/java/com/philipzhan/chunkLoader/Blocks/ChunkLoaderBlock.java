package com.philipzhan.chunkLoader.Blocks;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;

public class ChunkLoaderBlock extends Block {

    public ChunkLoaderBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        int chunkX = world.getChunk(pos).getPos().x;
        int chunkZ = world.getChunk(pos).getPos().z;

        if (!world.isClient) {
            // is server
            System.out.println("Set server chunk forced at x " + chunkX + ", z " + chunkZ);
            MinecraftServer server = world.getServer();
            ServerWorld serverWorld = server.getOverworld();
            WorldChunk worldChunk = serverWorld.getChunk(chunkX,chunkZ);
            serverWorld.setChunkForced(chunkX, chunkZ,true);
            ServerTickEvents.START_SERVER_TICK.register((listener) -> {
                int tickSpeed = serverWorld.getGameRules().getInt(GameRules.RANDOM_TICK_SPEED);
                serverWorld.tickChunk(worldChunk, tickSpeed);
                System.out.println("Ticked");
            });
        }
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);

        int chunkX = world.getChunk(pos).getPos().x;
        int chunkZ = world.getChunk(pos).getPos().z;

        if (!world.isClient()) {
            // is server
            System.out.println("Removed server forced chunk at x " + chunkX + ", z " + chunkZ);
            MinecraftServer server = world.getServer();
            ServerWorld serverWorld = server.getOverworld();
            serverWorld.setChunkForced(chunkX, chunkZ,false);
        }
    }

}
