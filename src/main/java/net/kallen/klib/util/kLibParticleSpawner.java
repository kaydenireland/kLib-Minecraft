package net.kallen.klib.util;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class kLibParticleSpawner {

    public void spawnTotemParticles(ServerPlayer pPlayer) {
        ServerLevel level = (ServerLevel) pPlayer.level();
        for (int i = 0; i < 50; i++) {
            double offsetX = (level.random.nextDouble() - 0.5) * 2.0;
            double offsetY = level.random.nextDouble() * 2.0;
            double offsetZ = (level.random.nextDouble() - 0.5) * 2.0;
            level.sendParticles(ParticleTypes.TOTEM_OF_UNDYING,
                    pPlayer.getX() + offsetX,
                    pPlayer.getY() + offsetY,
                    pPlayer.getZ() + offsetZ,
                    10,
                    0, 0, 0, .3);
        }

    }


}
