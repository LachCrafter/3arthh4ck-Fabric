package me.earth.earthhack.impl.util.minecraft;

import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.util.bind.Bind;
import me.earth.earthhack.pingbypass.input.Keyboard;
import net.minecraft.client.option.KeyBinding;

public class KeyBoardUtil
{
    public static boolean isKeyDown(KeyBinding binding)
    {
        return isKeyDown(binding.getDefaultKey().getCode());
    }

    public static boolean isKeyDown(Setting<Bind> setting)
    {
        return isKeyDown(setting.getValue());
    }

    public static boolean isKeyDown(Bind bind)
    {
        return isKeyDown(bind.getKey());
    }

    public static boolean isKeyDown(int key)
    {

        return key != 0 && key != -1
                && (key < 0
                ? true /*Mouse.isButtonDown(key + 100)*/
                : Keyboard.isKeyDown(key));
    }
}
