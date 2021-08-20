package com.philipzhan.chunkLoader.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

public class ChunkLoaderBlock extends Block {
    public ChunkLoaderBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        System.out.println("Placed one chunk loader block.");

        int chunkX = world.getChunk(pos).getPos().x;
        int chunkZ = world.getChunk(pos).getPos().z;

        if (!world.isClient) {
            // is server
            System.out.println("Set server chunk forced at x " + chunkX + ", z " + chunkZ);
            MinecraftServer server = world.getServer();
            ServerWorld serverWorld = server.getOverworld();
            serverWorld.setChunkForced(chunkX, chunkZ,true);
        }
    }
}
