package net.kallen.klib.item;


import net.kallen.klib.sound.kLibSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BellItem extends CoolDownItem {
    public BellItem(Properties pProperties) {
        super(pProperties.stacksTo(1));
    }



    @Override
    public InteractionResult use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        if (pLevel.isClientSide()) {

            if(pPlayer.getXRot() < -30){

                if(pPlayer.isCrouching()){
                    pLevel.playSound(pPlayer, pPlayer.blockPosition(), kLibSounds.BELL_UP_SNEAK.get(), SoundSource.PLAYERS,
                            .5f, 1);
                }else{
                    pLevel.playSound(pPlayer, pPlayer.blockPosition(), kLibSounds.BELL_UP.get(), SoundSource.PLAYERS,
                            .5f, 1);
                }

            }else if(pPlayer.getXRot() > 30){

                if(pPlayer.isCrouching()){
                    pLevel.playSound(pPlayer, pPlayer.blockPosition(), kLibSounds.BELL_DOWN_SNEAK.get(), SoundSource.PLAYERS,
                            .5f, 1);
                }else{
                    pLevel.playSound(pPlayer, pPlayer.blockPosition(), kLibSounds.BELL_DOWN.get(), SoundSource.PLAYERS,
                            .5f, 1);
                }

            }else{

                if(pPlayer.isCrouching()){
                    pLevel.playSound(pPlayer, pPlayer.blockPosition(), kLibSounds.BELL_STRAIGHT_SNEAK.get(), SoundSource.PLAYERS,
                            .5f, 1);
                }else{
                    pLevel.playSound(pPlayer, pPlayer.blockPosition(), kLibSounds.BELL_STRAIGHT.get(), SoundSource.PLAYERS,
                            .5f, 1);
                }

            }


        }

        return InteractionResult.SUCCESS;
    }

}