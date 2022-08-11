package me.josekat.supermarket.config;

import me.josekat.supermarket.files.MenuConfigFiles;

public class StartPageConfig {

    public static int[] slot = {0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,46,47,48,50,51,52,53};

    public static void setupStartPageConfig(){
        MenuConfigFiles.setup("startpage","shop");
        if(!MenuConfigFiles.get().isSet("menu")) {
            MenuConfigFiles.get().addDefault("options.title", "&6Market Menu");
            MenuConfigFiles.get().addDefault("options.ui-slot", 54);
            MenuConfigFiles.get().addDefault("options.fill", "AIR");
            setupBorder();
            MenuConfigFiles.get().options().copyDefaults(true);
            MenuConfigFiles.save();
        }
    }

    public static void setupBorder(){
        for(int number: slot) {
            MenuConfigFiles.get().addDefault("menu." + number + ".material","LIME_STAINED_GLASS_PANE" );
            MenuConfigFiles.get().addDefault("menu." + number + ".name","&f" );
            MenuConfigFiles.get().addDefault("menu." + number + ".lore","" );
            MenuConfigFiles.get().addDefault("menu." + number + ".action","" );
        }
        MenuConfigFiles.get().addDefault("menu.49.material","BARRIER" );
        MenuConfigFiles.get().addDefault("menu.49.name","&4Close Market" );
        MenuConfigFiles.get().addDefault("menu.49.lore","&cClick to Close" );
        MenuConfigFiles.get().addDefault("menu.49.action","CLOSE" );

    }
}
