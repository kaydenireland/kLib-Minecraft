package net.kallen.klib.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ToolMaterial;

public class SpearItem extends Item {
    public SpearItem(Properties properties,
                     ToolMaterial material,
                     float attackDuration,
                     float damageMultiplier,
                     float delay,
                     float dismountTime,
                     float dismountThreshold,
                     float knockbackTime,
                     float knockbackThreshold,
                     float damageTime,
                     float damageThreshold
                     ) {
        super(properties.spear(
                material, attackDuration, damageMultiplier, delay, dismountTime, dismountThreshold,
                knockbackTime, knockbackThreshold, damageTime, damageThreshold
        ));
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }


}
