package net.stellar.skillapiaddon;

import net.stellar.skillapiaddon.commands.CommandClanList;
import net.stellar.skillapiaddon.commands.CommandNpcAdd;
import net.stellar.skillapiaddon.commands.CommandNpcDel;
import net.stellar.skillapiaddon.commands.CommandNpcGetItem;
import net.stellar.skillapiaddon.commands.tabcompleter.TabCompleterNpcGetItem;
import net.stellar.skillapiaddon.listeners.ListenerOnEntityInteract;
import net.stellar.stellarcore.StellarCore;
import net.stellar.stellarcore.utils.Values;
import org.bukkit.Bukkit;

public final class SkillAPIAddon {

    public SkillAPIAddon(StellarCore stellarCore) {
        if (stellarCore.getConfig().getBoolean("skillapiaddon")) {
            startSkillAPIAddon(stellarCore);
        }
    }

    private void startSkillAPIAddon(StellarCore stellarCore) {
        registerCommands(stellarCore);
        registerEvents(stellarCore);
        registerTabCompleters(stellarCore);
        System.out.println(Values.PREFIX + "Enabled SkillAPIAddon");
    }

    private void registerEvents(StellarCore stellarCore) {
        Bukkit.getPluginManager().registerEvents(new ListenerOnEntityInteract(), stellarCore);
    }

    private void registerCommands(StellarCore stellarCore) {
        stellarCore.getCommand("npcadd").setExecutor(new CommandNpcAdd());
        stellarCore.getCommand("npcdel").setExecutor(new CommandNpcDel());
        stellarCore.getCommand("npcgetitem").setExecutor(new CommandNpcGetItem());
        stellarCore.getCommand("clanlist").setExecutor(new CommandClanList());
    }

    private void registerTabCompleters(StellarCore stellarCore) {
        stellarCore.getCommand("npcgetitem").setTabCompleter(new TabCompleterNpcGetItem());
    }
}
