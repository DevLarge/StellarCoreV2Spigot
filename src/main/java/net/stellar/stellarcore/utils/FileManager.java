package net.stellar.stellarcore.utils;

/*
The code in class FileManager, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from SkillAPI Addon, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import net.stellar.stellarcore.StellarCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    private static File fileNpcData;
    private static File fileNpcVillageData;
    private static File fileAnnouncements;
    private static YamlConfiguration yamlNpcData;
    private static YamlConfiguration yamlNpcVillageData;
    private static YamlConfiguration yamlAnnouncements;

    public static void initFiles(StellarCore stellarCore) {
        stellarCore.getConfig().options().copyDefaults();
        stellarCore.saveDefaultConfig();
        File dataFolder = Bukkit.getServer().getPluginManager().getPlugin("StellarCore").getDataFolder();
        File npcFolder = new File(dataFolder, "data");
        if (!npcFolder.exists()) npcFolder.mkdir();

        fileNpcData = new File(npcFolder, "npcdata.yml");
        fileNpcVillageData = new File(npcFolder, "npcvillagedata.yml");
        fileAnnouncements = new File(dataFolder, "announcements.yml");

        for (File f : Arrays.asList(fileNpcData, fileNpcVillageData, fileAnnouncements)) {
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        yamlNpcData = YamlConfiguration.loadConfiguration(fileNpcData);
        yamlNpcVillageData = YamlConfiguration.loadConfiguration(fileNpcVillageData);
        yamlAnnouncements = YamlConfiguration.loadConfiguration(fileAnnouncements);

        checkFiles();
    }

    private static void checkFiles() {
        if (!yamlAnnouncements.contains("announcements")) {
            yamlAnnouncements.set("enabled", true);
            yamlAnnouncements.set("delay", 60);
            yamlAnnouncements.set("required_players", 1);
            yamlAnnouncements.set("sound.enabled", true);
            yamlAnnouncements.set("sound.volume", 1.0);
            yamlAnnouncements.set("sound.pitch", 1.0);
            List<String> list1 = Arrays.asList("&r", "<center>&e&lANNOUNCEMENT</center>", "<center>&7Rate this plugin 5 stars on Spigot!</center>", "&r");
            yamlAnnouncements.set("announcements.list1", list1);
            List<String> list2 = Arrays.asList("&r", "<center>&e&lANNOUNCEMENT</center>", "<center>&7This plugin was created by &eGAY TYPICAL</center>", "&r");
            yamlAnnouncements.set("announcements.list2", list2);
            saveAnnouncements();
        }
    }

    public static void saveNpcData() {
        try {
            FileManager.yamlNpcData.save(fileNpcData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveNpcVillageData() {
        try {
            FileManager.yamlNpcVillageData.save(fileNpcVillageData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveAnnouncements() {
        try {
            FileManager.yamlAnnouncements.save(fileAnnouncements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getNpcLevel(String npcUuid) {
        if (!getNpcData().contains(npcUuid)) {
            return "null";
        } else {
            return getNpcData().get(npcUuid).toString();
        }

    }

    public static String getNpcVillage(String npcUuid) {
        if (!getNpcVillageData().contains(npcUuid)) {
            return "null";
        } else {
            return getNpcVillageData().get(npcUuid).toString();
        }
    }

    public static void addNpc(String npcUuid, int levelRequired) {
        getNpcData().set(npcUuid, levelRequired);
        saveNpcData();
    }

    public static void delNpc(String npcUuid) {
        getNpcData().set(npcUuid, null);
        saveNpcData();
    }

    public static void addNpcVillage(String uuid, String village) {
        getNpcVillageData().set(uuid, village);
        saveNpcVillageData();
    }
    public static void delNpcVillage(String uuid) {
        getNpcVillageData().set(uuid, null);
        saveNpcVillageData();
    }

    public static YamlConfiguration getNpcData() {
        return yamlNpcData;
    }

    public static YamlConfiguration getNpcVillageData() {
        return yamlNpcVillageData;
    }
    public static YamlConfiguration getAnnouncements() {
        return yamlAnnouncements;
    }
}
