package com.github.tacowasa059.advancementcopyrightplugin;

import com.github.tacowasa059.advancementcopyrightplugin.command.resetAchievements;
import com.github.tacowasa059.advancementcopyrightplugin.listener.PlayerJoin;
import com.github.tacowasa059.advancementcopyrightplugin.listener.achievementEvent;
import com.github.tacowasa059.advancementcopyrightplugin.scoreboard.AchievementCount;
import com.github.tacowasa059.advancementcopyrightplugin.scoreboard.AchievementList;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdvancementCopyrightPlugin extends JavaPlugin {
    public static AdvancementCopyrightPlugin plugin;
    public AchievementList achievement;
    public AchievementCount achievementCount;
    @Override
    public void onEnable() {
        // Plugin startup logic
        this.plugin=this;
        achievement=new AchievementList();
        achievementCount=new AchievementCount();

        getCommand("resetAchievement").setExecutor(new resetAchievements());
        getServer().getPluginManager().registerEvents(new achievementEvent(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
