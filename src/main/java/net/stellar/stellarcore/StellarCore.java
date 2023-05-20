package net.stellar.stellarcore;

import net.stellar.skillapiaddon.SkillAPIAddon;
import net.stellar.stellarcore.handlers.RunnableHandler;
import net.stellar.stellarcore.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class StellarCore extends JavaPlugin {

    @Override
    public void onEnable() {
        FileManager.initFiles(this);
        RunnableHandler.registerAll(this);
        SkillAPIAddon skillAPIAddon = new SkillAPIAddon(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

}