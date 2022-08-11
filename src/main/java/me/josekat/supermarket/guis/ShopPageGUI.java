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

public class ShopPageGUI {

    private static Inventory shopPage;

    public static void openShopPage(Player p, String shop){
        //setting from shopConfig
        int size = MenuConfigFiles.GetShopFile(shop).getInt("options.ui-slot");
        String name = ChatColor.translateAlternateColorCodes('&', MenuConfigFiles.GetShopFile(shop).getString("options.title"));
        String fillItem = MenuConfigFiles.GetShopFile(shop).getString("options.fill");
        shopPage = Bukkit.createInventory(p, size, name);
        // Fill ItemStack
        ItemStack fill = new ItemStack(Material.valueOf(fillItem));
        // Loop Menu Item
        for (int i = 0; i < size; i++) {
            // Setting from config
            String configMaterial = MenuConfigFiles.GetShopFile(shop).getString("menu." + i + ".material");
            if (configMaterial != null) {
                String configName = ChatColor.translateAlternateColorCodes('&', MenuConfigFiles.GetShopFile(shop).getString("menu." + i + ".name"));
                String configLore = ChatColor.translateAlternateColorCodes('&', MenuConfigFiles.GetShopFile(shop).getString("menu." + i + ".lore"));
                // Loop ItemStack
                ItemStack menuMaterial = new ItemStack(Material.valueOf(configMaterial));
                ItemMeta menuMeta = menuMaterial.getItemMeta();
                menuMeta.setDisplayName(configName);
                if (!configLore.isEmpty()) {
                    menuMeta.setLore(Collections.singletonList(configLore));
                }
                menuMaterial.setItemMeta(menuMeta);
                // set item
                shopPage.setItem(i,menuMaterial);
            } else {
                shopPage.setItem(i, fill);
            }
            p.openInventory(shopPage);
        }
    }

}
