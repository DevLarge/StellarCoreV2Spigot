package net.stellar.stellarcore.utils.chat;

/*
The code in class ChatUtil, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from StellarCore, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import org.bukkit.ChatColor;

public class ChatUtil {

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }


}
