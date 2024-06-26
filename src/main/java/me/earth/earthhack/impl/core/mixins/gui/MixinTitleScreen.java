package me.earth.earthhack.impl.core.mixins.gui;

import me.earth.earthhack.api.util.interfaces.Globals;
import me.earth.earthhack.impl.commands.gui.CommandGui;
import me.earth.earthhack.impl.commands.gui.EarthhackButton;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.TextIconButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen extends Screen implements Globals
{
    private MixinTitleScreen() {
        super(Text.of("3arthh4ck"));
    }

    @Inject(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/TitleScreen;addDrawableChild(Lnet" +
                            "/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;",
                    ordinal = 2,
                    shift = At.Shift.AFTER))
    @SuppressWarnings("ConstantConditions")
    public void buttonHook(CallbackInfo info)
    {
        TextIconButtonWidget earthhackButton = this.addDrawableChild(EarthhackButton.createEarthhackButton(20,
                action -> this.client.setScreen(new CommandGui(new TitleScreen())),
                true));
        int x = this.width / 2 + 2 + 98 + 4,
            y = this.height / 4 + 48 + 72 + 12 - (20 + 4 * 4);

        earthhackButton.setPosition(x, y);
        // TODO: adjust position if it is blocked, this is not very hard
    }
}
