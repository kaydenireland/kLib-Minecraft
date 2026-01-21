package net.kallen.klib.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CoolDownItem extends Item {
    public CoolDownItem(Properties pProperties) {
        super(pProperties);
    }


    public InteractionResult applyCooldown(Level pLevel, Player pPlayer, ItemStack stack, int coolDownTime, String tag){

        if (!pLevel.isClientSide() && !pPlayer.isCreative()) {
            CompoundTag persistentData = pPlayer.getPersistentData();

            long lastUsedTime = persistentData.getLongOr(tag, 0);
            long currentTime = pPlayer.level().getGameTime();

            if ((currentTime < lastUsedTime + coolDownTime) && lastUsedTime > 0) {
                return InteractionResult.FAIL;
            }

            persistentData.putLong(tag, currentTime);
            pPlayer.getCooldowns().addCooldown(stack, coolDownTime);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;

    }
}