package net.kallen.klib.mixin.entity;

import net.kallen.klib.Config;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Creeper.class)
public abstract class CreeperMixin extends Monster {

    @Shadow protected abstract void explodeCreeper();

    protected CreeperMixin(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void init(CallbackInfo info) {
        if (Config.ENABLE_CREEPER_CHAINING.isTrue()) {
            if (this.getLastDamageSource() != null &&
                    this.getLastDamageSource().is(DamageTypeTags.IS_EXPLOSION)
            ) {
                this.explodeCreeper();
            }
        }
    }
}