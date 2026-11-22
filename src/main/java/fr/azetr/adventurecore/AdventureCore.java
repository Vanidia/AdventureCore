package fr.azetr.adventurecore;

import org.bukkit.plugin.java.JavaPlugin;

public final class AdventureCore extends JavaPlugin {

    private QuestManager questManager;

    @Override
    public void onEnable() {

        questManager = new QuestManager(this);
        questManager.setup();

        getCommand("adventure").setExecutor(new AdventureCommand());
        getCommand("quests").setExecutor(new QuestsCommand(questManager));

        getServer().getPluginManager().registerEvents(new EventsListener(questManager), this);

        getServer().getLogger().info("Plugin AdventureCore start !");

    }

    public QuestManager getQuestManager() {
        return questManager;
    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("Plugin AdventureCore stop !");
    }
}
