package net.kallen.klib.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;

public class HallowedLogBlock extends RotatedPillarBlock {

    private final Block logBlock;
    private final Block strippedLogBlock;


    public HallowedLogBlock(Properties pProperties, Block logBlock, Block strippedLogBlock) {
        super(pProperties.strength(3f));
        this.logBlock = logBlock;
        this.strippedLogBlock = strippedLogBlock;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {

            if(state.is(logBlock)) {
                return strippedLogBlock.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}