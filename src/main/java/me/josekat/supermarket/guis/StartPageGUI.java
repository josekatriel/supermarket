package me.josekat.supermarket.guis;

import me.josekat.supermarket.files.MenuConfigFiles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class StartPageGUI {
    private static Inventory startPage;

    public static void openStartPage(Player p) {
        // Settings from config
        int size = MenuConfigFiles.get().getInt("options.ui-slot");
        String name = ChatColor.translateAlternateColorCodes('&', MenuConfigFiles.get().getString("options.title"));
        String fillItem = MenuConfigFiles.get().getString("options.fill");
        // Start
        startPage = Bukkit.createInventory(p, size, name);
        // Fill ItemStack
        ItemStack fill = new ItemStack(Material.valueOf(fillItem));

        // Loop Menu Item
        for (int i = 0; i < size; i++) {
            // Setting from config
            String configMaterial = MenuConfigFiles.get().getString("menu." + i + ".material");
            if (configMaterial != null) {
                String configName = ChatColor.translateAlternateColorCodes('&', MenuConfigFiles.get().getString("menu." + i + ".name"));
                String configLore = ChatColor.translateAlternateColorCodes('&', MenuConfigFiles.get().getString("menu." + i + ".lore"));
                // Loop ItemStack
                ItemStack menuMaterial = new ItemStack(Material.valueOf(configMaterial));
                ItemMeta menuMeta = menuMaterial.getItemMeta();
                menuMeta.setDisplayName(configName);
                if (!configLore.isEmpty()) {
                    menuMeta.setLore(Collections.singletonList(configLore));
                }
                menuMaterial.setItemMeta(menuMeta);
                // set item
                startPage.setItem(i,menuMaterial);
            } else {
                startPage.setItem(i, fill);
            }
            p.openInventory(startPage);
        }
    }
    public static Inventory getStartPage() {
        return startPage;
    }
}
