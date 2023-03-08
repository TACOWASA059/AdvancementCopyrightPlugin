package com.github.tacowasa059.advancementcopyrightplugin.listener;

import com.github.tacowasa059.advancementcopyrightplugin.AdvancementCopyrightPlugin;
import com.github.tacowasa059.advancementcopyrightplugin.scoreboard.AchievementList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import static org.apache.commons.lang.StringUtils.contains;

public class achievementEvent implements Listener {
    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent e){
        Advancement advancement=e.getAdvancement();

        //レシピの解放でも呼ばれるので、その場合はスルー
        if(advancement.getKey().toString().contains("minecraft:recipes"))return;
        if(AdvancementCopyrightPlugin.plugin.achievement.addScore(advancement.getKey().toString())){
            Player player= e.getPlayer();
            AdvancementCopyrightPlugin.plugin.achievementCount.addScore(player);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"advancement grant @a only "+advancement.getKey());
            Bukkit.broadcastMessage(ChatColor.DARK_AQUA+player.getName()+ChatColor.GREEN+"が実績を達成しました。");
        }
    }
}
