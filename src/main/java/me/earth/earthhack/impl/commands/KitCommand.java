package me.earth.earthhack.impl.commands;

import me.earth.earthhack.api.util.interfaces.Globals;
import me.earth.earthhack.impl.commands.abstracts.AbstractStackCommand;
import me.earth.earthhack.impl.commands.util.CommandDescriptions;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;

public class KitCommand extends AbstractStackCommand implements Globals
{
    public static final ItemStack KIT;

    static
    {
        ItemStack stack = new ItemStack(Blocks.LIGHT_BLUE_SHULKER_BOX);

        ItemStack helmet = new ItemStack(Items.NETHERITE_HELMET);
        helmet.setCustomName(Text.of("3arth's H3lmet"));
        helmet.addEnchantment(Enchantments.UNBREAKING, 3);
        helmet.addEnchantment(Enchantments.PROTECTION, 4);
        helmet.addEnchantment(Enchantments.MENDING, 1);
        helmet.addEnchantment(Enchantments.RESPIRATION, 3);
        helmet.addEnchantment(Enchantments.AQUA_AFFINITY, 1);

        ItemStack chestplate = new ItemStack(Items.NETHERITE_CHESTPLATE);
        chestplate.setCustomName(Text.of("3arth's Ch3stpl4te"));
        chestplate.addEnchantment(Enchantments.PROTECTION, 4);
        chestplate.addEnchantment(Enchantments.UNBREAKING, 3);
        chestplate.addEnchantment(Enchantments.MENDING, 1);

        ItemStack leggings = new ItemStack(Items.NETHERITE_LEGGINGS);
        leggings.setCustomName(Text.of("3arth's P4nts"));
        leggings.addEnchantment(Enchantments.BLAST_PROTECTION, 4);
        leggings.addEnchantment(Enchantments.UNBREAKING, 3);
        leggings.addEnchantment(Enchantments.MENDING, 1);

        ItemStack boots = new ItemStack(Items.NETHERITE_BOOTS);
        boots.setCustomName(Text.of("3arth's B0ots"));
        boots.addEnchantment(Enchantments.PROTECTION, 4);
        boots.addEnchantment(Enchantments.UNBREAKING, 3);
        boots.addEnchantment(Enchantments.MENDING, 1);
        boots.addEnchantment(Enchantments.FEATHER_FALLING, 4);
        boots.addEnchantment(Enchantments.DEPTH_STRIDER, 3);

        ItemStack pickaxe = new ItemStack(Items.NETHERITE_PICKAXE);
        pickaxe.setCustomName(Text.of("German Efficiency"));
        pickaxe.addEnchantment(Enchantments.EFFICIENCY, 5);
        pickaxe.addEnchantment(Enchantments.FORTUNE, 3);
        pickaxe.addEnchantment(Enchantments.MENDING, 1);
        pickaxe.addEnchantment(Enchantments.UNBREAKING, 3);

        ItemStack sword = new ItemStack(Items.NETHERITE_SWORD);
        sword.addEnchantment(Enchantments.FIRE_ASPECT, 2);
        sword.addEnchantment(Enchantments.MENDING, 1);
        sword.addEnchantment(Enchantments.UNBREAKING, 3);
        sword.addEnchantment(Enchantments.SHARPNESS, 5);

        NbtList nbtList = new NbtList();
        nbtList.add(0, helmet.writeNbt(nbtCompoundSlot(0)));
        nbtList.add(1, helmet.writeNbt(nbtCompoundSlot(9)));

        nbtList.add(2, chestplate.writeNbt(nbtCompoundSlot(1)));
        nbtList.add(3, chestplate.writeNbt(nbtCompoundSlot(10)));

        nbtList.add(4, leggings.writeNbt(nbtCompoundSlot(2)));
        nbtList.add(5, leggings.writeNbt(nbtCompoundSlot(11)));

        nbtList.add(6, boots.writeNbt(nbtCompoundSlot(3)));
        nbtList.add(7, boots.writeNbt(nbtCompoundSlot(12)));

        nbtList.add(8, pickaxe.writeNbt(nbtCompoundSlot(4)));
        nbtList.add(9, sword.writeNbt(nbtCompoundSlot(5)));

        nbtList.add(10, new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 64).writeNbt(nbtCompoundSlot(6)));
        nbtList.add(11, new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 64).writeNbt(nbtCompoundSlot(7)));

        nbtList.add(12, new ItemStack(Items.ENDER_CHEST, 64).writeNbt(nbtCompoundSlot(8)));

        nbtList.add(13, new ItemStack(Items.END_CRYSTAL, 64).writeNbt(nbtCompoundSlot(13)));
        nbtList.add(14, new ItemStack(Items.END_CRYSTAL, 64).writeNbt(nbtCompoundSlot(14)));
        nbtList.add(15, new ItemStack(Items.END_CRYSTAL, 64).writeNbt(nbtCompoundSlot(15)));
        nbtList.add(16, new ItemStack(Items.END_CRYSTAL, 64).writeNbt(nbtCompoundSlot(16)));
        nbtList.add(17, new ItemStack(Items.END_CRYSTAL, 64).writeNbt(nbtCompoundSlot(17)));

        nbtList.add(18, new ItemStack(Items.TOTEM_OF_UNDYING).writeNbt(nbtCompoundSlot(18)));
        nbtList.add(19, new ItemStack(Items.TOTEM_OF_UNDYING).writeNbt(nbtCompoundSlot(19)));
        nbtList.add(20, new ItemStack(Items.TOTEM_OF_UNDYING).writeNbt(nbtCompoundSlot(20)));
        nbtList.add(21, new ItemStack(Items.TOTEM_OF_UNDYING).writeNbt(nbtCompoundSlot(21)));

        nbtList.add(22, new ItemStack(Items.EXPERIENCE_BOTTLE, 64).writeNbt(nbtCompoundSlot(22)));
        nbtList.add(23, new ItemStack(Items.EXPERIENCE_BOTTLE, 64).writeNbt(nbtCompoundSlot(23)));
        nbtList.add(24, new ItemStack(Items.EXPERIENCE_BOTTLE, 64).writeNbt(nbtCompoundSlot(24)));
        nbtList.add(25, new ItemStack(Items.EXPERIENCE_BOTTLE, 64).writeNbt(nbtCompoundSlot(25)));
        nbtList.add(26, new ItemStack(Items.EXPERIENCE_BOTTLE, 64).writeNbt(nbtCompoundSlot(26)));

        NbtCompound nbt = new NbtCompound();
        nbt.put("Items", nbtList);

        NbtCompound blockEntityTag = new NbtCompound();
        blockEntityTag.put("Items", nbtList);
        blockEntityTag.putString("id", "minecraft:shulker_box");

        stack.getOrCreateNbt().put("BlockEntityTag", blockEntityTag);
        KIT = stack;
    }

    private static NbtCompound nbtCompoundSlot(int slot)
    {
        NbtCompound helmetNbt = new NbtCompound();
        helmetNbt.putByte("Slot", (byte) slot);
        return helmetNbt;
    }

    public KitCommand()
    {
        super("kit", "kit");
        CommandDescriptions.register(this, "Gives you a kit.");
    }

    @Override
    protected ItemStack getStack(String[] args)
    {
        return KIT.copy();
    }

}