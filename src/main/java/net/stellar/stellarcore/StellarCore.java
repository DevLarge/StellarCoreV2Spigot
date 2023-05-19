package net.stellar.stellarcore;

import net.stellar.skillapiaddon.SkillAPIAddon;
import net.stellar.stellarcore.utils.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class StellarCore extends JavaPlugin {

    private SkillAPIAddon skillAPIAddon;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        this.skillAPIAddon = new SkillAPIAddon(this);
        FileManager.initFiles();
        // TESTIJNG
    }

    @Override
    public void onDisable() {
    }

    public SkillAPIAddon getSkillAPIAddon() {
        return skillAPIAddon;
    }
}