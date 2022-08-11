package me.josekat.supermarket;

import me.josekat.supermarket.commands.BalanceCommand;
import me.josekat.supermarket.commands.MarketCommand;
import me.josekat.supermarket.config.StartPageConfig;
import me.josekat.supermarket.listeners.BlockBreakListener;
import me.josekat.supermarket.listeners.StartPageListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperMarket extends JavaPlugin{

    public static SuperMarket plugin;
    private static Economy econ = null;

    @Override
    public void onEnable() {
        plugin = this;
        // Vault
        if (!setupEconomy() ) {
            System.out.println("No economy found!. Disabling Supermarket");
            //getServer().getPluginManager().disablePlugin(this);
            return;
        }
        // Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Custom Config for Shop
        initConfig();

        // Command
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("market").setExecutor(new MarketCommand());
        // Listener
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new StartPageListener(), this);

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    private void initConfig(){
        StartPageConfig.setupStartPageConfig();
    }
}
