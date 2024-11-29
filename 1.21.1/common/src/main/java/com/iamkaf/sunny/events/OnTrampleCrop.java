package com.iamkaf.sunny.events;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class OnTrampleCrop {
    public static void init() {
        InteractionEvent.FARMLAND_TRAMPLE.register(OnTrampleCrop::execute);
    }

    // because you shouldn't worry about the little things when farming
    private static EventResult execute(Level level, BlockPos blockPos, BlockState blockState, float v, Entity entity) {
        return EventResult.interruptFalse();
    }
}
