package net.kallen.klib.tag;

import net.kallen.klib.kLib;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class kLibTags {

    public static class Blocks {


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(kLib.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> AMETHYST_REPAIRABLE = createTag("amethyst_repairable");
        public static final TagKey<Item> ECHO_SHARD_REPAIRABLE = createTag("echo_shard_repairable");
        public static final TagKey<Item> GOAT_HORN = createTag("goat_horn");
        public static final TagKey<Item> BELL = createTag("bell");



        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(kLib.MODID, name));
        }
    }
}
