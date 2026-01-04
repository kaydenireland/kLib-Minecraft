package net.kallen.klib.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;


public class HornItem extends Item {

    private SoundEvent sound;
    public HornItem(Properties pProperties, SoundEvent sound) {
        super(pProperties.stacksTo(1));
        this.sound = sound;
    }

    @Override
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand hand) {
        ItemStack stack = pPlayer.getItemInHand(hand);

        if (!pPlayer.isUsingItem() && !pPlayer.getCooldowns().isOnCooldown(stack)) {
            pPlayer.startUsingItem(hand);
            pPlayer.playSound(sound, 1.0F, 1.0F);
            pPlayer.getCooldowns().addCooldown(stack, 100);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.TOOT_HORN;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 40;
    }


}