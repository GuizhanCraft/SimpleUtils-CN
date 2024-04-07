package io.github.mooy1.simpleutils;

import java.io.File;
import java.util.logging.Level;

import net.guizhanss.guizhanlibplugin.updater.GuizhanUpdater;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPluginLoader;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.mooy1.infinitylib.metrics.bukkit.Metrics;
import io.github.mooy1.infinitylib.metrics.charts.SimplePie;
import io.github.mooy1.simpleutils.implementation.Items;

public final class SimpleUtils extends AbstractAddon {

    public SimpleUtils(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file,
                "SlimefunGuguProject", "SimpleUtils", "master", "auto-update");
    }

    public SimpleUtils() {
        super("SlimefunGuguProject", "SimpleUtils", "master", "auto-update");
    }

    @Override
    protected void enable() {
        if (!getServer().getPluginManager().isPluginEnabled("GuizhanLibPlugin")) {
            getLogger().log(Level.SEVERE, "本插件需要 鬼斩前置库插件(GuizhanLibPlugin) 才能运行!");
            getLogger().log(Level.SEVERE, "从此处下载: https://50l.cc/gzlib");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (autoUpdatesEnabled() && getPluginVersion().startsWith("Build")) {
            GuizhanUpdater.start(this, getFile(), "SlimefunGuguProject", "SimpleUtils", "master");
        }

        Items.setup(this);
        Metrics metrics = new Metrics(this, 10285);
        String ixInstalled = String.valueOf(getServer().getPluginManager().isPluginEnabled("InfinityExpansion"));
        String autoUpdates = String.valueOf(autoUpdatesEnabled());
        metrics.addCustomChart(new SimplePie("ix_installed", () -> ixInstalled));
        metrics.addCustomChart(new SimplePie("auto_updates", () -> autoUpdates));
    }

    @Override
    protected void disable() {

    }

}
