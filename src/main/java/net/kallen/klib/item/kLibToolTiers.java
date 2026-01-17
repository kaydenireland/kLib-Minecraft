package net.kallen.klib.item;

import net.kallen.klib.tag.kLibTags;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.common.Tags;

public class kLibToolTiers {

    public static final ToolMaterial AMETHYST = new ToolMaterial(Tags.Blocks.NEEDS_GOLD_TOOL,
            16, 6f, 6f, 35, kLibTags.Items.AMETHYST_REPAIRABLE);

    public static final ToolMaterial ECHO_SHARD = new ToolMaterial(Tags.Blocks.NEEDS_GOLD_TOOL,
            128, 5f, 1f, 28, kLibTags.Items.ECHO_SHARD_REPAIRABLE);

}
