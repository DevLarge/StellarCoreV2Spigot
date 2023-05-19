package net.stellar.skillapiaddon.commands;

/*
The code in class CommandClanList, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from SkillAPI Addon, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.stellar.skillapiaddon.utils.Villages;
import net.stellar.stellarcore.utils.Permissions;
import net.stellar.stellarcore.utils.Values;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClanList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission(Permissions.QUESTER)) {
                player.sendMessage(Permissions.NO_PERM);
                return false;
            }
            player.sendMessage(Villages.VILLAGES.toString());
        } else {
            System.out.println(Values.PREFIX + Villages.VILLAGES.toString());
        }
        return false;
    }
}
