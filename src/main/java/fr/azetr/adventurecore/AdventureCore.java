package fr.azetr.adventurecore;

import org.bukkit.plugin.java.JavaPlugin;

public final class AdventureCore extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("adventure").setExecutor(new AdventureCommand());

        getServer().getLogger().info("Plugin AdventureCore start !");

    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("Plugin AdventureCore stop !");
    }
}
