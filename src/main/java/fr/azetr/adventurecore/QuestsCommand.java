package fr.azetr.adventurecore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class QuestsCommand implements CommandExecutor {

    private final QuestManager questManager;

    public QuestsCommand(QuestManager questManager) {
        this.questManager = questManager;
    }

    @Override
    public boolean onCommand (@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;

        player.openInventory(InvManager.QuestsInv(player, questManager));

        return true;
    }
}
