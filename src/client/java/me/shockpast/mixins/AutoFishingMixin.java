package me.shockpast.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.Hand;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.shockpast.modules.AutoFishing;

@Mixin(FishingBobberEntity.class)
public class AutoFishingMixin {
    @Shadow private boolean caughtFish;

    @Inject(method = "onTrackedDataSet", at = @At("TAIL"))
    private void onTrackedDataSet(TrackedData<?> data, CallbackInfo info) {
        if (!AutoFishing.isEnabled())
            return;

        if (!caughtFish)
            return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null)
            return;

        client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
        AutoFishing.setTicks(2);
    }
}