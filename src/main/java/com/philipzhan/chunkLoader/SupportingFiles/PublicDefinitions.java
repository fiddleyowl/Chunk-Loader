package com.philipzhan.chunkLoader.SupportingFiles;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public class PublicDefinitions {

    public static final String modId = "chunkloader";

    public static ArrayList<Integer> chunkXs = new ArrayList<>();

    public static ArrayList<Integer> chunkZs = new ArrayList<>();

    public static boolean shouldTickChunk(ChunkPos chunkPos, Vec3d playerPos) {
        double chunkPosCenterX = chunkPos.getCenterX();
        double chunkPosCenterZ = chunkPos.getCenterZ();
        double playerX = playerPos.x;
        double playerZ = playerPos.z;
        if (Math.sqrt(Math.pow(chunkPosCenterX - playerX,2) + Math.pow(chunkPosCenterZ - playerZ,2)) > 128.0) {
            return true;
        } else {
            return false;
        }
    }
}
