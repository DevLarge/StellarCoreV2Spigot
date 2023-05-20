package net.stellar.skillapiaddon.listeners;

/*
The code in class ListenerOnEntityInteract, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from SkillAPI Addon, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.player.PlayerData;
import net.stellar.stellarcore.utils.Items;
import net.stellar.stellarcore.utils.Permissions;
import net.stellar.stellarcore.utils.Values;
import net.stellar.stellarcore.utils.chat.ChatUtil;
import net.stellar.stellarcore.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.HashMap;

public class ListenerOnEntityInteract implements Listener {

    private final HashMap<Player, String> promptMap = new HashMap<>();
    private final HashMap<Player, String> promptVillageMap = new HashMap<>();

    @EventHandler
    public void onPlayerLevelInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = SkillAPI.getPlayerData(Bukkit.getOfflinePlayer(player.getUniqueId()));
        String uuid = event.getRightClicked().getUniqueId().toString();
        if (playerData.getMainClass() == null) {
            player.sendMessage(Values.PREFIX + ChatUtil.color("&cYou need a Clan!"));
            event.setCancelled(true);
            return;
        }
        int level = playerData.getMainClass().getLevel();
        if (!FileManager.getNpcData().contains(uuid)) {
            return;
        }

        if (Integer.parseInt(FileManager.getNpcLevel(uuid)) > level && (!player.hasPermission(Permissions.BYPASS) || !player.isOp())) {
            player.sendMessage(Values.PREFIX + ChatUtil.color("&8Your level is too low to interact with this NPC!"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerVillageInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = SkillAPI.getPlayerData(Bukkit.getOfflinePlayer(player.getUniqueId()));
        String uuid = event.getRightClicked().getUniqueId().toString();
        String playerVillage = playerData.getMainClass().getData().getName();
        if (!FileManager.getNpcData().contains(uuid)) {
            return;
        }

        if (!FileManager.getNpcVillage(uuid).equals(playerVillage) && (!player.hasPermission(Permissions.BYPASS) || !player.isOp())) {
            player.sendMessage(Values.PREFIX + ChatUtil.color("&8Your village does not match the NPC!"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        String uuid = event.getRightClicked().getUniqueId().toString();
        String level;
        String village;
        if (!player.hasPermission(Permissions.QUESTER)) return;
        if (player.getInventory().getItemInMainHand() == null) return;
        if (player.getInventory().getItemInMainHand().getItemMeta() == null) return;
        if (player.getInventory().getItemInMainHand().getItemMeta().getLocalizedName() == null) return;
        String localizedName = player.getInventory().getItemInMainHand().getItemMeta().getLocalizedName();

        switch (localizedName) {
            case Items.ITEM_NPC_ID_NAME:
                if (FileManager.getNpcData().contains(uuid)) {
                    level = FileManager.getNpcLevel(uuid);
                } else {
                    player.sendMessage(Values.PREFIX + "The entity level has not been registered!");
                    level = "null";
                }

                if (FileManager.getNpcVillageData().contains(uuid)) {
                    village = FileManager.getNpcVillage(uuid);
                } else {
                    player.sendMessage(Values.PREFIX + "The entity village has not been registered!");
                    village = "null";
                }
                player.sendMessage(Values.PREFIX + "The npc level is: " + level + ", and the village is " + village);
                /*
                StringSelection stringSelection = new StringSelection(uuid);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, stringSelection);
                player.sendMessage(Values.PREFIX + "Copied to clipboard");
                 */
                break;
            case Items.ITEM_NPC_ADD_NAME:
                promptMap.put(player, uuid);
                player.sendMessage(Values.PREFIX + ChatUtil.color(uuid + " Has been added to the database \n" +
                        "Please Enter NPC Level Requirement: "));
                break;
            case Items.ITEM_NPC_DEL_NAME:
                FileManager.delNpc(uuid);
                FileManager.delNpcVillage(uuid);
                player.sendMessage(Values.PREFIX + ChatUtil.color("&cThe npc: &f" + uuid + " &cno longer has a level requirement or a village!"));
                break;
            case Items.ITEM_NPC_VILLAGE:
                promptVillageMap.put(player, uuid);
                player.sendMessage(Values.PREFIX + ChatUtil.color(uuid + " Has been added to the database \n" +
                        "Please Enter NPC Village: "));
                break;

        }
    }

    @EventHandler
    public void onChatLevelEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (promptMap.containsKey(player)) {
            FileManager.addNpc(promptMap.get(player), Integer.parseInt(event.getMessage()));
            player.sendMessage(Values.PREFIX + promptMap.get(player) + " set required level to " + event.getMessage());
            event.setCancelled(true);
            promptMap.remove(player);
        }
    }
    @EventHandler
    public void onChatVillageEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (promptVillageMap.containsKey(player)) {
            FileManager.addNpcVillage(promptVillageMap.get(player), event.getMessage());
            player.sendMessage(Values.PREFIX + promptVillageMap.get(player) + " set village  " + event.getMessage());
            event.setCancelled(true);
            promptVillageMap.remove(player);
        }
    }
}
