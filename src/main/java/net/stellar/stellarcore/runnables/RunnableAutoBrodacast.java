package net.stellar.stellarcore.runnables;

/*
The code in class RunnableAutoBrodacast, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from StellarCore, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.stellar.stellarcore.StellarCore;
import net.stellar.stellarcore.utils.FileManager;
import net.stellar.stellarcore.utils.chat.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunnableAutoBrodacast implements Runnable {

    private Map<Integer, List<String>> broadcasts;
    private int broadcastTask = 0;
    private int count = 0;
    private int size = 0;
    private int requiredPlayers = 0;

    // ESSENTIALS X SOUND
    private Sound sound = null;
    private double volume;
    private double pitch;

    public RunnableAutoBrodacast(StellarCore stellarCore) {
        YamlConfiguration config = FileManager.getAnnouncements();

        broadcasts = new HashMap<>();
        int count = 0;
        for (String key : config.getConfigurationSection("announcements").getKeys(false)) {
            broadcasts.put(count, config.getStringList("announcements." + key));
            count++;
        }

        if (config.getBoolean("sound.enabled")) {
            sound = Sound.BLOCK_NOTE_PLING;
            volume = config.getDouble("sound.volume");
            pitch = config.getDouble("sound.pitch");
        }

        requiredPlayers = config.getInt("required_players", 0);

        size = broadcasts.size();
        if (size > 0)
            broadcastTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(stellarCore, this, 60L, config.getInt("delay") * 20);
    }

    @Override
    public void run() {
        if (count == size) count = 0;

        if (count < size && Bukkit.getOnlinePlayers().size() >= requiredPlayers) {
            for (Player player : Bukkit.getOnlinePlayers()) {

                broadcasts.get(count).forEach(message -> {
                    if (message.contains("<center>") && message.contains("</center>"))
                        message = TextUtil.getCenteredMessage(message);
                    player.sendMessage(TextUtil.color(message));
                });

                if (sound != null) player.playSound(player.getLocation(), sound, (float) volume, (float) pitch);
            }
            count++;
        }

    }
}
