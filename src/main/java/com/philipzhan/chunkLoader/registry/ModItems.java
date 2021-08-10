package com.philipzhan.chunkLoader.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.philipzhan.chunkLoader.SupportingFiles.PublicDefinitions.modId;

public class ModItems {

    public static final BlockItem CHUNK_LOADER = new BlockItem(ModBlocks.CHUNK_LOADER, new Item.Settings().fireproof().rarity(Rarity.COMMON).group(ItemGroup.MISC));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(modId, "chunk_loader"), CHUNK_LOADER);
    }
}
