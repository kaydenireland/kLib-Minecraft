package net.kallen.klib.block;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;

public class StrippableLogBlock extends SimpleLogBlock {

    private final Block strippedLogBlock;


    public StrippableLogBlock(Properties pProperties, Block strippedLogBlock) {
        super(pProperties.strength(3f));
        this.strippedLogBlock = strippedLogBlock;
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
                return strippedLogBlock.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}