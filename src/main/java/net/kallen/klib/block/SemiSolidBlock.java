package net.kallen.klib.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SemiSolidBlock extends Block {

    private TagKey<EntityType<?>> eTag;
    private boolean allowProjectiles;

    public SemiSolidBlock(Properties pProperties, TagKey<EntityType<?>> eTag) {
        super(pProperties);
        this.eTag = eTag;
        this.allowProjectiles = false;
    }

    public SemiSolidBlock(Properties pProperties, TagKey<EntityType<?>> eTag, boolean allowProjectiles) {
        super(pProperties);
        this.eTag = eTag;
        this.allowProjectiles = allowProjectiles;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext eContext) {
            Entity entity = eContext.getEntity();

            if (entity != null) {

                if (!allowProjectiles && entity instanceof Projectile) {
                    return Shapes.block();
                }

                if (entity.getType().is(eTag)) {
                    return Shapes.block();
                }

            }
        }

        return Shapes.empty();
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }


}
