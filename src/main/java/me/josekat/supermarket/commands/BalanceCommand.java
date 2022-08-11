package me.josekat.supermarket.commands;

import me.josekat.supermarket.SuperMarket;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player player){
            Economy economy = SuperMarket.getEconomy();
            String money = economy.format(economy.getBalance(player));

            if(args.length == 0){
                player.sendMessage(ChatColor.GREEN + "Current Balance: " + money);
            } else if (args.length == 2 && args[0].equalsIgnoreCase("withdraw")){
                double withdrawAmount = Double.valueOf(args[1]);
                EconomyResponse response = economy.withdrawPlayer(player, withdrawAmount);
                if(response.transactionSuccess()){
                    player.sendMessage("You have successfully removed " + economy.format(response.amount));
                    player.sendMessage("Your new balance is: " + economy.format(response.balance));
                } else {
                    player.sendMessage("Failed to withdraw money from your balance");
                    player.sendMessage(response.errorMessage);
                }

            }
        }

        return true;
    }
}
