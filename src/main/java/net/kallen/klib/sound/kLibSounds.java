package net.kallen.klib.sound;

import net.kallen.klib.kLib;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Supplier;

public class kLibSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, kLib.MODID);

    public static final Supplier<SoundEvent> BELL_UP = registerSoundEvent("bell_up");
    public static final Supplier<SoundEvent> BELL_UP_SNEAK = registerSoundEvent("bell_up_sneak");
    public static final Supplier<SoundEvent> BELL_STRAIGHT = registerSoundEvent("bell_straight");
    public static final Supplier<SoundEvent> BELL_STRAIGHT_SNEAK = registerSoundEvent("bell_straight_sneak");
    public static final Supplier<SoundEvent> BELL_DOWN = registerSoundEvent("bell_down");
    public static final Supplier<SoundEvent> BELL_DOWN_SNEAK = registerSoundEvent("bell_down_sneak");


    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(kLib.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}