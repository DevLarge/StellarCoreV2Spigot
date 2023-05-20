package net.stellar.stellarcore;

import net.stellar.skillapiaddon.SkillAPIAddon;
import net.stellar.stellarcore.handlers.RunnableHandler;
import net.stellar.stellarcore.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class StellarCore extends JavaPlugin {

    private SkillAPIAddon skillAPIAddon;

    @Override
    public void onEnable() {
        FileManager.initFiles(this);
        RunnableHandler.registerAll(this);
        this.skillAPIAddon = new SkillAPIAddon(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

    public SkillAPIAddon getSkillAPIAddon() {
        return skillAPIAddon;
    }
}