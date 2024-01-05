package me.pianopenguin.sessionlogin.mixin;

import me.pianopenguin.sessionlogin.gui.SessionLoginScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMultiplayer.class)
public class GuiMultiplayerMixin extends GuiScreen implements GuiYesNoCallback {
    @Inject(method = "initGui", at=@At("TAIL"))
    public void onInitGui(CallbackInfo ci) {
        this.buttonList.add(new GuiButton(1337, 5, 6, 98, 20, "Custom Session"));
    }

    @Inject(method = "actionPerformed", at=@At("HEAD"))
    public void onActionPerformed(GuiButton guiButton, CallbackInfo ci) {
        if (guiButton.id == 1337)
            Minecraft.getMinecraft().displayGuiScreen(new SessionLoginScreen(this));
    }
}
