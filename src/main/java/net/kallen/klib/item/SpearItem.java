package net.kallen.klib.item;

import net.minecraft.world.item.Item;
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
}
