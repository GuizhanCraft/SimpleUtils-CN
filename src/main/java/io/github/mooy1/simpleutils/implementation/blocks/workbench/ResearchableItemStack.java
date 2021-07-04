package io.github.mooy1.simpleutils.implementation.blocks.workbench;

import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.mooy1.infinitylib.items.StackUtils;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.researching.Research;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

final class ResearchableItemStack extends CustomItem {

    private final Research research;
    private final String name;
    
    ResearchableItemStack(Research research, ItemStack item) {
        super(item);
        this.research = research;
        this.name = StackUtils.getDisplayName(item);
    }
    
    boolean cantCraft(Player p) {
        Optional<PlayerProfile> prof = PlayerProfile.find(p);
        if (prof.isPresent()) {
            if (prof.get().hasUnlocked(this.research)) {
                return false;
            } else {
                p.sendMessage(ChatColor.RED + "你必须解锁物品 " + this.name + ChatColor.RED + " 才能合成它!");
            }
        }
        return true;
    }
    
}
