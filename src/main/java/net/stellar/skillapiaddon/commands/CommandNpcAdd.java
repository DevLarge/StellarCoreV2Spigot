package net.stellar.skillapiaddon.commands;

/*
The code in class CommandAddNpc, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from SkillAPI Addon, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.stellar.stellarcore.utils.Permissions;
import net.stellar.stellarcore.utils.FileManager;
import net.stellar.stellarcore.utils.Values;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandNpcAdd implements CommandExecutor {
    public final String USAGE = "/npcadd <uuid> <level>";
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission(Permissions.QUESTER)) return false;

            if (args.length < 2) {
                player.sendMessage(Values.PREFIX + "Need to have an argument! " + USAGE);
                return false;
            }
            FileManager.addNpc(args[0], Integer.parseInt(args[1]));
            player.sendMessage(Values.PREFIX + args[0] + " set required level to " + args[1]);
            return false;
        } else {
            // TODO: CONSOLE
        }
        return false;
    }
}
