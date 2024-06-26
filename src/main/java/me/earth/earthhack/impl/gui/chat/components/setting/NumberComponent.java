package me.earth.earthhack.impl.gui.chat.components.setting;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.setting.settings.NumberSetting;
import me.earth.earthhack.impl.gui.chat.clickevents.SmartClickEvent;
import me.earth.earthhack.impl.gui.chat.components.SettingComponent;
import me.earth.earthhack.impl.gui.chat.components.SuppliedComponent;
import me.earth.earthhack.impl.gui.chat.components.values.ValueComponent;
import me.earth.earthhack.impl.gui.chat.factory.IComponentFactory;
import me.earth.earthhack.impl.gui.chat.util.ChatComponentUtil;
import me.earth.earthhack.impl.gui.chat.util.IncrementationUtil;
import me.earth.earthhack.impl.modules.client.commands.Commands;
import me.earth.earthhack.impl.util.text.TextColor;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class NumberComponent<N extends Number, E extends NumberSetting<N>>
        extends SettingComponent<N, NumberSetting<N>>
{
    public static final IComponentFactory<?, ?>
            FACTORY = new NumberComponentFactory<>();

    public NumberComponent(E setting)
    {
        super(setting);

        if (!(setting.getContainer() instanceof Module))
        {
            this.append(new ValueComponent(setting));
            return;
        }

        Module module = (Module) setting.getContainer();

        HoverEvent numberHover = new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                    new SuppliedComponent(() -> setting.getName()
                        + " <"
                        + setting.getValue().toString()
                        + "> "
                        + setting.getInputs(null)));

        HoverEvent plus;
        HoverEvent minus;

        if (setting.isFloating())
        {
            plus = new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new SuppliedComponent(() -> "Increment "
                            + setting.getName()
                            + " to "
                            + TextColor.AQUA
                            + getNewValue(true)
                            + TextColor.WHITE
                            + " by 0.1. Hold: " + TextColor.RED + "ALT "
                            + TextColor.WHITE + ": 1.0, " + TextColor.RED
                            + "RCTRL " + TextColor.WHITE + ": Max,"
                            + " " + TextColor.RED + "LCTRL " + TextColor.WHITE
                            + ": 5%, " + TextColor.RED + "LCTRL + ALT "
                            + TextColor.WHITE + ": 10%"));

            minus = new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new SuppliedComponent(() -> "Decrement "
                            + setting.getName()
                            + " to "
                            + TextColor.AQUA
                            + getNewValue(false)
                            + TextColor.WHITE
                            + " by 0.1. Hold: " + TextColor.RED + "ALT "
                            + TextColor.WHITE + ": 1.0, " + TextColor.RED
                            + "RCTRL " + TextColor.WHITE + ": Min,"
                            + " " + TextColor.RED + "LCTRL " + TextColor.WHITE
                            + ": 5%, " + TextColor.RED + "LCTRL + ALT "
                            + TextColor.WHITE + ": 10%"));
        }
        else
        {
            plus = new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new SuppliedComponent(() -> "Increment "
                            + setting.getName()
                            + " to "
                            + TextColor.AQUA
                            + getNewValue(true)
                            + TextColor.WHITE
                            + " by 1. Hold: " + TextColor.RED + "ALT "
                            + TextColor.WHITE + ": 10, " + TextColor.RED
                            + "RCTRL " + TextColor.WHITE + ": Max,"
                            + " " + TextColor.RED + "LCTRL " + TextColor.WHITE
                            + ": 5%, " + TextColor.RED + "LCTRL + ALT "
                            + TextColor.WHITE + ": 10%"));

            minus = new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new SuppliedComponent(() -> "Decrement "
                            + setting.getName()
                            + " to "
                            + TextColor.AQUA
                            + getNewValue(false)
                            + TextColor.WHITE
                            + " by 1. Hold: " + TextColor.RED + "ALT "
                            + TextColor.WHITE + ": 10, " + TextColor.RED
                            + "RCTRL " + TextColor.WHITE + ": Min,"
                            + " " + TextColor.RED + "LCTRL "
                            + TextColor.WHITE
                            + ": 5%, " + TextColor.RED + "LCTRL + ALT "
                            + TextColor.WHITE + ": 10%"));
        }

        this.append(Text.empty().append(TextColor.GRAY
                        + " + "
                        + TextColor.WHITE)
                .setStyle(Style.EMPTY
                        .withHoverEvent(ChatComponentUtil.setOffset(plus))
                        .withClickEvent(
                            new SmartClickEvent
                                (ClickEvent.Action.RUN_COMMAND)
                            {
                                @Override
                                public String getValue()
                                {
                                    return Commands.getPrefix()
                                            + "hiddensetting "
                                            + module.getName()
                                            + " "
                                            + "\"" + setting.getName() + "\""
                                            + " " + getNewValue(true);
                                }
                            })));

        this.append(new ValueComponent(setting)
                .setStyle(Style.EMPTY
                    .withHoverEvent(ChatComponentUtil.setOffset(numberHover))
                    .withClickEvent(new ClickEvent(
                            ClickEvent.Action.RUN_COMMAND,
                            Commands.getPrefix()
                                    + "hiddensetting "
                                    + module.getName()
                                    + " "
                                    + "\"" + setting.getName() + "\""))));

        this.append(Text.empty().append(
                TextColor.GRAY + " - " + TextColor.RESET)
                .setStyle(Style.EMPTY
                        .withHoverEvent(ChatComponentUtil.setOffset(minus))
                        .withClickEvent(
                                new SmartClickEvent
                                        (ClickEvent.Action.RUN_COMMAND)
                                {
                                    @Override
                                    public String getValue()
                                    {
                                        return Commands.getPrefix()
                                                + "hiddensetting "
                                                + module.getName()
                                                + " "
                                                + "\""
                                                + setting.getName()
                                                + "\""
                                                + " "
                                                + getNewValue(false);
                                    }
                                })));
    }

    private String getNewValue(boolean plus)
    {
        String value;

        if (setting.isFloating())
        {
            value = IncrementationUtil.crD(setting.getValue() + "",
                    setting.getMin() + "",
                    setting.getMax() + "",
                    !plus);
        }
        else
        {
            value = IncrementationUtil.crL(setting.getValue().longValue(),
                    setting.getMin().longValue(),
                    setting.getMax().longValue(),
                    !plus) + "";
        }

        return value;
    }

    private static final class NumberComponentFactory<F extends Number>
            implements IComponentFactory<F, NumberSetting<F>>
    {
        @Override
        public SettingComponent<F, NumberSetting<F>>
                                                create(NumberSetting<F> setting)
        {
            return new NumberComponent<>(setting);
        }
    }

}
