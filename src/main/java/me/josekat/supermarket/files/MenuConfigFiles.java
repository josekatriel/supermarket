package me.josekat.supermarket.files;

import me.josekat.supermarket.SuperMarket;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MenuConfigFiles {

    private static File file;
    private static File shopFile;
    protected static FileConfiguration customFile;

    //Finds or generates the custom config file
    public static void setup(String name, String folder) {
        String path = name + ".yml";
        if (folder != null) {
            path = folder + "/" + path;
            File directory = new File(SuperMarket.plugin.getDataFolder() + "/" + folder);
            if (!directory.exists()) {
                try {
                    directory.mkdir();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        file = new File(SuperMarket.plugin.getDataFolder(), path);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Fatal error! Config Setup Fail. File name: " + name);
            }
        }

        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return customFile;
    }

    public static void save() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            System.out.println("Couldn't save file :" + e);
        }
    }

    public void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
        System.out.println("Reload config file!");
    }

    public static FileConfiguration GetShopFile(String name){
        shopFile = new File(SuperMarket.plugin.getDataFolder(), "shop/" + name + ".yml");
        if(!shopFile.exists()){
            System.out.println("Shop file not found based on action: " + name + ".yml");
            return null;
        }
        return YamlConfiguration.loadConfiguration(shopFile);
    }

}
