package net.kallen.klib.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.HashSet;
import java.util.Set;

public class NeighborStateBlock extends Block {

    public static final BooleanProperty STATE = BooleanProperty.create("on");

    public NeighborStateBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(STATE, false));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return updateState(state, level, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STATE);
    }

    private InteractionResult updateState(BlockState state, Level level, BlockPos pos) {
        if (!level.isClientSide()) {
            boolean currentState = state.getValue(STATE);
            boolean newState = !currentState;
            Set<BlockPos> processed = new HashSet<>();

            // Start the recursive update from this position
            viewNeighbors(state, level, pos, newState, processed);
        }

        return InteractionResult.SUCCESS;
    }

    private void viewNeighbors(BlockState state, Level level, BlockPos pos, boolean newState, Set<BlockPos> processed) {
        // Skip if already processed
        if (processed.contains(pos)) {
            return;
        }

        // Mark as processed
        processed.add(pos);

        // Update this block
        level.setBlockAndUpdate(pos, state.setValue(STATE, newState));

        // Update neighbors
        updateNeighbor(level, pos.above(), newState, processed);
        updateNeighbor(level, pos.below(), newState, processed);
        updateNeighbor(level, pos.north(), newState, processed);
        updateNeighbor(level, pos.south(), newState, processed);
        updateNeighbor(level, pos.east(), newState, processed);
        updateNeighbor(level, pos.west(), newState, processed);
    }

    private void updateNeighbor(Level level, BlockPos pos, boolean newState, Set<BlockPos> processed) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof NeighborStateBlock) {
            viewNeighbors(state, level, pos, newState, processed);
        }
    }

}