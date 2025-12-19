package net.kallen.klib.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.EnumSet;


public class MagicMirrorItem extends CoolDownItem {

    private static final String COOLDOWN_TAG = "MirrorCooldown";
    private final int cooldownTime;
    private final SoundEvent sound;

    // Constructors with Custom Sound
    public MagicMirrorItem(Properties pProperties, int cooldownTime, SoundEvent sound) {
        super(pProperties.stacksTo(1));
        this.sound = sound;
        this.cooldownTime = cooldownTime;
    }

    public MagicMirrorItem(Properties pProperties, int cooldownTime, SoundEvent sound, int durability) {
        super(pProperties.stacksTo(1).durability(durability));
        this.sound = sound;
        this.cooldownTime = cooldownTime;
    }

    // Constructors without Custom Sound
    public MagicMirrorItem(Properties pProperties, int cooldownTime) {
        super(pProperties.stacksTo(1));
        this.sound = SoundEvents.ENDERMAN_TELEPORT;
        this.cooldownTime = cooldownTime;
    }

    public MagicMirrorItem(Properties pProperties, int cooldownTime, int durability) {
        super(pProperties.stacksTo(1).durability(durability));
        this.sound = SoundEvents.ENDERMAN_TELEPORT;
        this.cooldownTime = cooldownTime;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {

            ServerPlayer.RespawnConfig respawn = serverPlayer.getRespawnConfig();
            EnumSet<Relative> relatives = EnumSet.noneOf(Relative.class);

            if (respawn != null) {

                BlockPos spawnPos = respawn.respawnData().pos();
                ResourceKey<Level> dimension = respawn.respawnData().dimension();
                float yaw = respawn.respawnData().yaw();

                ServerLevel targetDimension = serverPlayer.level().getServer().getLevel(dimension);


                if (targetDimension != null && spawnPos != null) {
                    serverPlayer.teleportTo(
                            targetDimension,
                            spawnPos.getX() + 0.5,
                            spawnPos.getY() + 0.5,
                            spawnPos.getZ() + 0.5,
                            relatives,
                            yaw,
                            serverPlayer.getXRot(),
                            true
                    );
                }
            }else {
                serverPlayer.sendSystemMessage(Component.literal(
                        "You do not have a respawn point set."
                ));
                return InteractionResult.FAIL;

            }


            level.playSound(
                    null,
                    serverPlayer.blockPosition(),
                    sound,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );

            serverPlayer.getCooldowns().addCooldown(stack, cooldownTime);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.CONSUME;
    }


}