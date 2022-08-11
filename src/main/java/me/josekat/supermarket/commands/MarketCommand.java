package me.josekat.supermarket.commands;

import me.josekat.supermarket.guis.StartPageGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MarketCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player player){
            StartPageGUI.openStartPage(player);
        }
        return true;
    }
}
