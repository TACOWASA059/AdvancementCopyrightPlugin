package com.github.tacowasa059.advancementcopyrightplugin.command;

import com.github.tacowasa059.advancementcopyrightplugin.AdvancementCopyrightPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class resetAchievements implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player =(Player) sender;
            if(player.isOp()){
                for(Player player1:Bukkit.getOnlinePlayers()){
                    player1.getInventory().clear();
                }
                player.sendMessage(ChatColor.GREEN+"全てのプレイヤーのインベントリをクリアしました。");
                AdvancementCopyrightPlugin.plugin.achievement.resetScore();
                AdvancementCopyrightPlugin.plugin.achievementCount.resetScore();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"advancement revoke @a everything");
                player.sendMessage(ChatColor.GREEN+"全てのプレイヤーの実績をリセットしました。");
            }
        }
        return true;
    }
}
