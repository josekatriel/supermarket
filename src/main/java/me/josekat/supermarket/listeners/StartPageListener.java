package me.josekat.supermarket.listeners;

import me.josekat.supermarket.files.MenuConfigFiles;
import me.josekat.supermarket.guis.ShopPageGUI;
import me.josekat.supermarket.guis.StartPageGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class StartPageListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Inventory StartPage = StartPageGUI.getStartPage();
        Player p = (Player) e.getWhoClicked();
        int slotClicked = e.getRawSlot();
        String Action = MenuConfigFiles.get().getString("menu." + slotClicked + ".action");
        if (e.getInventory().equals(StartPage)) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(null)) {
                return;
            }
            if (Action.equalsIgnoreCase("CLOSE")){
                p.closeInventory();
            } else if(Action.equalsIgnoreCase("PAGE")) {
                p.sendMessage("Next Page");
            } else if (Action.equalsIgnoreCase(null)){
                p.sendMessage("Shop not found");
            } else {
                try {
                    MenuConfigFiles.GetShopFile(Action);
                    ShopPageGUI.openShopPage(p,Action);
                } catch (Exception ex){
                    p.sendMessage("Shop not found");
                }

            }
        }

    }
}
