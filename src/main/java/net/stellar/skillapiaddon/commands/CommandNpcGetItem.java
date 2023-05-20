package net.stellar.skillapiaddon.commands;

/*
The code in class CommandGetItemNpcId, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from SkillAPI Addon, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.stellar.stellarcore.utils.Items;
import net.stellar.stellarcore.utils.Permissions;
import net.stellar.stellarcore.utils.Values;
import net.stellar.stellarcore.utils.chat.ChatUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class CommandNpcGetItem implements CommandExecutor {

    private final String USAGE = ChatUtil.color("&cNeed arguments! <id/add/del/village>");
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (!player.hasPermission(Permissions.QUESTER)) return false;
        Inventory inv = player.getInventory();
        if (inv.firstEmpty() == -1) {
            player.sendMessage(Values.PREFIX + "Inventory can't be full!");
            return false;
        }
        if (args.length == 0) {
            player.sendMessage(Values.PREFIX + USAGE);
            return false;
        }

        if (args[0].equalsIgnoreCase("id")) {
            inv.addItem(generateItemStackId());
            player.sendMessage(Values.PREFIX + ChatUtil.color("Added item stack Npc ID"));
        } else if (args[0].equalsIgnoreCase("add")) {
            inv.addItem(generateItemStackAdd());
            player.sendMessage(Values.PREFIX + ChatUtil.color("Added item stack &5Npc Adder"));
        } else if (args[0].equalsIgnoreCase("del")) {
            inv.addItem(generateItemStackDel());
            player.sendMessage(Values.PREFIX + ChatUtil.color("Added item stack &cNpc Deleter"));
        } else if (args[0].equalsIgnoreCase("village")) {
            inv.addItem(generateItemStackVillage());
            player.sendMessage(Values.PREFIX + ChatUtil.color("Added item stack &2Npc Village Selector"));
        } else {
            player.sendMessage(Values.PREFIX + ChatUtil.color("&cHas to be a valid argument!"));
        }
        return false;
    }

    private ItemStack generateItemStackId() {
        ItemStack itemNpcId = new ItemStack(Material.COMPASS, 1);
        ItemMeta itemMeta = itemNpcId.getItemMeta();

        itemMeta.setLocalizedName(Items.ITEM_NPC_ID_NAME);
        itemMeta.setDisplayName(ChatUtil.color("&bNpc Id"));
        itemMeta.setLore(Collections.singletonList(ChatUtil.color("Right-click to get the NPC ID")));
        itemNpcId.setItemMeta(itemMeta);

        return itemNpcId;
    }

    private ItemStack generateItemStackAdd() {
        ItemStack itemNpcId = new ItemStack(Material.BOOK, 1);
        ItemMeta itemMeta = itemNpcId.getItemMeta();

        itemMeta.setLocalizedName(Items.ITEM_NPC_ADD_NAME);
        itemMeta.setDisplayName(ChatUtil.color( "&b&5Npc Adder"));
        itemMeta.setLore(Collections.singletonList(ChatUtil.color( "Right-click to add a level requirement")));
        itemNpcId.setItemMeta(itemMeta);

        return itemNpcId;
    }

    private ItemStack generateItemStackDel() {
        ItemStack itemNpcId = new ItemStack(Material.BARRIER, 1);
        ItemMeta itemMeta = itemNpcId.getItemMeta();

        itemMeta.setLocalizedName(Items.ITEM_NPC_DEL_NAME);
        itemMeta.setDisplayName(ChatUtil.color("&b&cNpc Deleter"));
        itemMeta.setLore(Collections.singletonList(ChatUtil.color( "Right-click to delete the level requirement")));
        itemNpcId.setItemMeta(itemMeta);

        return itemNpcId;
    }

    private ItemStack generateItemStackVillage() {
        ItemStack itemNpcVillage = new ItemStack(Material.END_ROD, 1);
        ItemMeta itemMeta = itemNpcVillage.getItemMeta();

        itemMeta.setLocalizedName(Items.ITEM_NPC_VILLAGE);
        itemMeta.setDisplayName(ChatUtil.color( "&b&2Npc Village selector"));
        itemMeta.setLore(Collections.singletonList(ChatUtil.color( "Right-click to set the village of the NPC")));
        itemNpcVillage.setItemMeta(itemMeta);

        return itemNpcVillage;
    }

}
