package net.kallen.klib.item;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CoolDownItem extends Item {
    public CoolDownItem(Properties pProperties) {
        super(pProperties);
    }


    public void applyCooldown(Player pPlayer, ItemStack stack, int coolDownTime){
        pPlayer.getCooldowns().addCooldown(stack, coolDownTime);
    }
}