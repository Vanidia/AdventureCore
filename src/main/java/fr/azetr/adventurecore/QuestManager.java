package fr.azetr.adventurecore;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class QuestManager {

    private final JavaPlugin plugin;
    private File questsFile;
    private FileConfiguration questsConfig;

    private File playersFile;
    private FileConfiguration playersConfig;

    public QuestManager (JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        questsFile = new File(plugin.getDataFolder(), "quests.yml");
        playersFile = new File(plugin.getDataFolder(), "players.yml");

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        try {
            if (!questsFile.exists()) {
                plugin.saveResource("quests.yml", false);
            }

            if (!playersFile.exists()) {
                playersFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        questsConfig = YamlConfiguration.loadConfiguration(questsFile);
        playersConfig = YamlConfiguration.loadConfiguration(playersFile);
    }

    public void savePlayers() {
        try {
            playersConfig.save(playersFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveQuests() {
        try {
            questsConfig.save(questsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startQuest(Player player, String questId) {

        String uuid = player.getUniqueId().toString();
        String path = "players." + uuid + ".quests." + questId;

        if (!questsConfig.contains("quests." + questId)) {
            player.sendMessage("Cette quête n'existe pas !");
            return;
        }

        if (playersConfig.contains(path)) {
            player.sendMessage("Tu as déjà commencé cette quête !");
            return;
        }

        if (hasActiveQuest(player)) {
            player.sendMessage("Tu as déjà une autre quête en cours !");
            return;
        }

        playersConfig.set(path + ".progress", 0);
        playersConfig.set(path + ".completed", false);

        savePlayers();
        player.sendMessage("Tu as commencé la quête !");
    }

    public Set<String> getQuestIds() {
        if (questsConfig.getConfigurationSection("quests") == null) {
            return Collections.emptySet();
        }

        return questsConfig.getConfigurationSection("quests").getKeys(false);
    }

    public String getQuestName(String questId) {
        return questsConfig.getString("quests." + questId + ".name");
    }

    public String getQuestDescription(String questId) {
        return questsConfig.getString("quests." + questId + ".description");
    }

    public Material getQuestMaterial(String questId) {
        String material = questsConfig.getString("quests." + questId + ".material");

        return Material.valueOf(material);
    }

    public int getQuestObjective(String questId) {
        return questsConfig.getInt("quests." + questId + ".objective");
    }

    public void progressQuest(Player player, String questId) {

        String uuid = player.getUniqueId().toString();
        String path = "players." + uuid + ".quests." + questId;

        if (!playersConfig.contains(path)) return;

        if (playersConfig.getBoolean(path + ".completed")) return;

        int progress = playersConfig.getInt(path + ".progress");
        int objective = getQuestObjective(questId);

        progress++;

        if (progress >= objective) {
            progress = objective;
            playersConfig.set(path + ".completed", true);
            player.sendMessage("§aQuête terminée !");
        }

        playersConfig.set(path + ".progress", progress);
        savePlayers();
    }

    public boolean hasActiveQuest(Player player) {

        String path = "players." + player.getUniqueId() + ".quests";

        if (!playersConfig.contains(path)) return false;

        for (String questId : playersConfig.getConfigurationSection(path).getKeys(false)) {

            boolean completed = playersConfig.getBoolean(
                    path + "." + questId + ".completed"
            );

            if (!completed) {
                return true;
            }
        }

        return false;
    }

    public int getQuestProgress(Player player, String questId) {
        String path = "players." + player.getUniqueId() + ".quests." + questId + ".progress";

        return playersConfig.getInt(path, 0);
    }

    public boolean isQuestCompleted(Player player, String questId) {
        String path = "players." + player.getUniqueId() + ".quests." + questId + ".completed";

        return playersConfig.getBoolean(path, false);
    }

    public boolean hasStartedQuest(Player player, String questId) {
        String path = "players." + player.getUniqueId() + ".quests." + questId;

        return playersConfig.contains(path);
    }
}
