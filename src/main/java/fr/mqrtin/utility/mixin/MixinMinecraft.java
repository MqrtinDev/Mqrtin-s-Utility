package fr.mqrtin.utility.mixin;

import fr.mqrtin.utility.Main;
import fr.mqrtin.utility.event.EventManager;
import fr.mqrtin.utility.event.events.LeftClickMouseEvent;
import fr.mqrtin.utility.event.events.RightClickMouseEvent;
import fr.mqrtin.utility.module.modules.QOL.NoClickDelay;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Shadow
    private int leftClickCounter;

    @Inject(method = "clickMouse", at = @At("HEAD"), cancellable = true)
    private void onClickMouse(CallbackInfo callbackInfo) {
        if (Main.moduleManager != null && Main.moduleManager.modules.get(NoClickDelay.class).isEnabled()) {
            this.leftClickCounter = 0;
        }
        LeftClickMouseEvent event = new LeftClickMouseEvent();
        EventManager.call(event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "rightClickMouse", at = @At("HEAD"), cancellable = true)
    private void onRightClickMouse(CallbackInfo callbackInfo) {
        RightClickMouseEvent event = new RightClickMouseEvent();
        EventManager.call(event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }
}

