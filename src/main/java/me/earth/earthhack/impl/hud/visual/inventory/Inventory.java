package me.earth.earthhack.impl.hud.visual.inventory;

import me.earth.earthhack.api.hud.HudCategory;
import me.earth.earthhack.api.hud.HudElement;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.ColorSetting;
import me.earth.earthhack.impl.managers.Managers;
import me.earth.earthhack.impl.util.client.SimpleHudData;
import me.earth.earthhack.impl.util.render.Render2DUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.awt.*;

public class Inventory extends HudElement {

    private final Setting<Boolean> xCarry =
            register(new BooleanSetting("RenderXCarry", false));
    private final Setting<Boolean> box =
            register(new BooleanSetting("Box", true));
    private final Setting<Boolean> pretty =
            register(new BooleanSetting("Pretty", true));
    private final Setting<Color> boxColor =
            register(new ColorSetting("BoxColor", new Color(23,23,23,23)));
    private final Setting<Color> outlineColor =
            register(new ColorSetting("OutlineColor", new Color(23,23,23,23)));


    private void render(DrawContext context) {
        if (mc.player != null) {
            if (box.getValue()) {
                if (pretty.getValue())
                    Render2DUtil.roundedRect(context.getMatrices(), getX(), getY() - 1.0f, getX() + 9 * 18, getY() + 55.0f, 2, boxColor.getValue().getRGB());
                else
                    Render2DUtil.drawBorderedRect(context.getMatrices(), getX(), getY(), getX() + 9 * 18, getY() + 55.0f, 1.0f, boxColor.getValue().getRGB(), outlineColor.getValue().getRGB());
            }

            ItemRender(context, mc.player.getInventory().main, (int) getX(), (int) getY(), xCarry.getValue());
        }
    }

    protected void ItemRender(DrawContext context, final DefaultedList<ItemStack> items, final int x, final int y, boolean xCarry) {
        for (int i = 0; i < items.size() - 9; i++) {
            int iX = x + (i % 9) * (18);
            int iY = y + (i / 9) * (18);
            ItemStack itemStack = items.get(i + 9);
            context.drawItem(itemStack, iX, iY, 100203, (int) getZ());
            // Managers.TEXT.drawString(context, String.valueOf(itemStack.getCount()), iX, iY, 0xffffffff);
        }

        if (xCarry) {
            for (int i = 1; i < 5; i++) {
                int iX = x + ((i + 4) % 9) * (18);
                ItemStack itemStack = mc.player.getInventory().getStack(i);
                if (itemStack != null && !itemStack.isEmpty()) {
                    context.drawItem(itemStack, iX, y - 18, 100204, (int) getZ());
                    Managers.TEXT.drawString(context, String.valueOf(itemStack.getCount()), iX, y - 18, 0xffffffff);
                }
            }
        }
    }

    public Inventory() {
        super("Inventory",  HudCategory.Visual, 2, 5);
        this.setData(new SimpleHudData(this, "Displays your inventory"));
    }

    @Override
    public void guiDraw(DrawContext context, int mouseX, int mouseY, float partialTicks) {
        super.guiDraw(context, mouseX, mouseY, partialTicks);
        render(context);
    }

    @Override
    public void hudDraw(DrawContext context) {
        render(context);
    }

    @Override
    public void guiUpdate(int mouseX, int mouseY) {
        super.guiUpdate(mouseX, mouseY);
        setWidth(getWidth());
        setHeight(getHeight());
    }

    @Override
    public void hudUpdate() {
        super.hudUpdate();
        setWidth(getWidth());
        setHeight(getHeight());
    }

    @Override
    public float getWidth() {
        return 9 * 18;
    }

    @Override
    public float getHeight() {
        return 55.0f;
    }

    private enum Mode {
        Box,
        OutLine
    }

}