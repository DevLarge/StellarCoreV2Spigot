package net.stellar.stellarcore.utils;

/*
The code in class FileManager, is not to be used by anyone, without legitimate permission from https://github.com/DevLarge, Oscar#8373. 
No other versions are to be created from SkillAPI Addon, unless you specifically got permission, and the source code from the author. 
Copyright (C) 2023 DevLarge
*/

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static File fileNpcData;
    private static File fileNpcVillageData;
    private static YamlConfiguration yamlConfigurationNpcData;
    private static YamlConfiguration yamlConfigurationNpcVillageData;

    public static void initFiles() {
        fileNpcData = new File(Bukkit.getServer().getPluginManager().getPlugin("StellarCore").getDataFolder(), "npcdata.yml");
        fileNpcVillageData = new File(Bukkit.getServer().getPluginManager().getPlugin("StellarCore").getDataFolder(), "npcvillagedata.yml");

        if (!fileNpcData.exists()) {
            try {
                fileNpcData.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!fileNpcVillageData.exists()) {
            try {
                fileNpcVillageData.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        yamlConfigurationNpcData = YamlConfiguration.loadConfiguration(fileNpcData);
        yamlConfigurationNpcVillageData = YamlConfiguration.loadConfiguration(fileNpcVillageData);

    }

    public static void saveFileNpcData() {
        try {
            FileManager.yamlConfigurationNpcData.save(fileNpcData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFileNpcVillageData() {
        try {
            FileManager.yamlConfigurationNpcVillageData.save(fileNpcVillageData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getNpcLevel(String npcUuid) {
        if (!getYamlConfigurationNpcData().contains(npcUuid)) {
            return "null";
        } else {
            return getYamlConfigurationNpcData().get(npcUuid).toString();
        }

    }

    public static String getNpcVillage(String npcUuid) {
        if (!getYamlConfigurationNpcVillageData().contains(npcUuid)) {
            return "null";
        } else {
            return getYamlConfigurationNpcVillageData().get(npcUuid).toString();
        }
    }

    public static void addNpc(String npcUuid, int levelRequired) {
        getYamlConfigurationNpcData().set(npcUuid, levelRequired);
        saveFileNpcData();
    }

    public static void delNpc(String npcUuid) {
        getYamlConfigurationNpcData().set(npcUuid, null);
        saveFileNpcData();
    }

    public static void addNpcVillage(String uuid, String village) {
        getYamlConfigurationNpcVillageData().set(uuid, village);
        saveFileNpcVillageData();
    }
    public static void delNpcVillage(String uuid) {
        getYamlConfigurationNpcVillageData().set(uuid, null);
        saveFileNpcVillageData();
    }

    public static File getFileNpcData() {
        return fileNpcData;
    }

    public static YamlConfiguration getYamlConfigurationNpcData() {
        return yamlConfigurationNpcData;
    }

    public static File getFileNpcVillageData() {
        return fileNpcVillageData;
    }

    public static YamlConfiguration getYamlConfigurationNpcVillageData() {
        return yamlConfigurationNpcVillageData;
    }
}
