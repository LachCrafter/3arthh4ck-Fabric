package me.earth.earthhack.impl.gui.chat.components;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.impl.core.ducks.util.IText;
import me.earth.earthhack.impl.core.util.SimpleTextFormatHook;
import me.earth.earthhack.impl.gui.chat.AbstractTextComponent;
import me.earth.earthhack.impl.gui.chat.factory.ComponentFactory;
import me.earth.earthhack.impl.modules.client.commands.Commands;
import me.earth.earthhack.impl.util.text.TextColor;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;

/**
 * A SettingComponent.
 *
 * SettingComponents can be created with the
 * {@link ComponentFactory}.
 *
 * @param <S> the Type of Setting.
 * @param <T> the Type of Settings value for this component.
 */
public abstract class SettingComponent<T, S extends Setting<T>>
        extends AbstractTextComponent
{
    protected final S setting;

    /**
     * Constructs a SettingComponent for the given setting.
     *
     * @param setting the Setting.
     */
    public SettingComponent(S setting)
    {
        super(setting.getName());

        this.setting = setting;
        this.setStyle(Style.EMPTY
                .withHoverEvent(ComponentFactory.getHoverEvent(setting)));

        ((IText) this)
                .earthhack$setFormattingHook(new SimpleTextFormatHook(this));
        ((IText) this)
                .earthhack$setUnFormattedHook(new SimpleTextFormatHook(this));

        if (setting.getContainer() instanceof Module)
        {
            (this.getStyle()).withClickEvent(
                    new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                            Commands.getPrefix()
                                    + "hiddensetting "
                                    + ((Module) setting.getContainer()).getName()
                                    + " "
                                    + "\"" + setting.getName() + "\""));
        }
    }

    @Override
    public String getText()
    {
        return setting.getName() + TextColor.GRAY + " : " + TextColor.WHITE;
    }

    @Override
    public String getUnformattedComponentText()
    {
        return this.getText();
    }

    @Override
    public MutableText copy()
    {
        return ComponentFactory.create(setting);
    }

}
