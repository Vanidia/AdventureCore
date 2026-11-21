package fr.azetr.adventurecore;

import org.bukkit.plugin.java.JavaPlugin;

public final class AdventureCore extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getLogger().info("Pligin start !");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
