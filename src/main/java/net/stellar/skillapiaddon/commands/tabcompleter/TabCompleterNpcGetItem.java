package net.stellar.skillapiaddon.commands.tabcompleter;

/*
The code in class TabCompleterNpcGetItem, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from SkillAPI Addon, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.stellar.stellarcore.utils.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class TabCompleterNpcGetItem implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender.hasPermission(Permissions.QUESTER)) {
            if (cmd.getName().equalsIgnoreCase("npcgetitem")) {
                if (args.length == 1) {
                    return Arrays.asList("id", "add", "del", "village");
                }
            }
        }
        return null;
    }
}
