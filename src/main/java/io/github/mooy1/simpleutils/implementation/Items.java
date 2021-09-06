package io.github.mooy1.simpleutils.implementation;

import java.util.Arrays;

import javax.annotation.Nonnull;

import lombok.experimental.UtilityClass;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import io.github.mooy1.simpleutils.SimpleUtils;
import io.github.mooy1.simpleutils.implementation.blocks.Elevator;
import io.github.mooy1.simpleutils.implementation.blocks.Sieve;
import io.github.mooy1.simpleutils.implementation.blocks.Workbench;
import io.github.mooy1.simpleutils.implementation.tools.Wrench;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

@UtilityClass
public final class Items {

    public static final SlimefunItemStack WRENCH = new SlimefunItemStack(
            "SIMPLE_WRENCH",
            Material.IRON_HOE,
            "&6简易扳手",
            "&e右键快速破坏货运、电容、机器"
    );
    public static final SlimefunItemStack SIEVE = new SlimefunItemStack(
            "SIMPLE_SIEVE",
            Material.COMPOSTER,
            "&6简易筛子",
            "&7将沙砾筛成矿粉或其他材料"
    );
    public static final SlimefunItemStack ELEVATOR = new SlimefunItemStack(
            "SIMPLE_ELEVATOR",
            Material.QUARTZ_BLOCK,
            "&f简易电梯",
            "&7下蹲往下, 跳跃往上"
    );
    public static final SlimefunItemStack WORKBENCH = new SlimefunItemStack(
            "SIMPLE_WORKBENCH",
            Material.CRAFTING_TABLE,
            "&6融合工作台",
            "&7既可以合成原版工作台物品",
            "&7也可以合成高级工作台物品"
    );

    public static void setup(@Nonnull SimpleUtils plugin) {
        ItemGroup category = new ItemGroup(SimpleUtils.createKey("main"),
                new CustomItemStack(Material.COMPOSTER, "&6简易工具"), 0);

        new Workbench(category, WORKBENCH, RecipeType.ENHANCED_CRAFTING_TABLE,
                Arrays.copyOf(new ItemStack[] {new ItemStack(Material.CRAFTING_TABLE)}, 9)
        ).register(plugin);

        new Sieve(category, SIEVE, new ItemStack[] {
                null, null, null,
                null, new ItemStack(Material.OAK_TRAPDOOR), null,
                null, new ItemStack(Material.COMPOSTER), null
        }, BlockFace.SELF).register(plugin);

        new Elevator(category, ELEVATOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                new ItemStack(Material.QUARTZ_BLOCK), new ItemStack(Material.QUARTZ_BLOCK), new ItemStack(Material.QUARTZ_BLOCK),
                new ItemStack(Material.QUARTZ_BLOCK), new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.QUARTZ_BLOCK),
                new ItemStack(Material.QUARTZ_BLOCK), new ItemStack(Material.QUARTZ_BLOCK), new ItemStack(Material.QUARTZ_BLOCK)
        }).register(plugin);

        new Wrench(category, WRENCH, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT,
                null, SlimefunItems.SILVER_INGOT, null,
                null, SlimefunItems.ALUMINUM_INGOT, null
        }).register(plugin);
    }

}
