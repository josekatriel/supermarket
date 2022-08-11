package me.josekat.supermarket.listeners;

import me.josekat.supermarket.SuperMarket;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Economy eco = SuperMarket.getEconomy();
        Player p = event.getPlayer();

        EconomyResponse response = eco.depositPlayer(p,5.0);
        if(response.transactionSuccess()){
            p.sendMessage(eco.format(response.amount));
        }
    }


}
