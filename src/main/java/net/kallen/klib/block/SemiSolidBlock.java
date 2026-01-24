package net.kallen.klib.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
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

    public SemiSolidBlock(Properties properties, TagKey<EntityType<?>> eTag) {
        super(properties.noOcclusion());
        this.eTag = eTag;
        this.allowProjectiles = false;
    }

    public SemiSolidBlock(Properties properties, TagKey<EntityType<?>> eTag, boolean allowProjectiles) {
        super(properties.noOcclusion());
        this.eTag = eTag;
        this.allowProjectiles = allowProjectiles;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext eContext) {
            Entity entity = eContext.getEntity();
            if (entity != null) {

                // Flags Arrows as Always Colliding
                if (entity instanceof AbstractArrow) {
                    return Shapes.block();
                }

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
    protected VoxelShape getEntityInsideCollisionShape(BlockState state, BlockGetter level, BlockPos pos, Entity entity) {
        // Always return full block shape for entities inside
        // This keeps arrows from falling through
        if (entity instanceof AbstractArrow || (!allowProjectiles && entity instanceof Projectile) || entity.getType().is(eTag)) {
            return Shapes.block();
        }
        return Shapes.empty();
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }
}