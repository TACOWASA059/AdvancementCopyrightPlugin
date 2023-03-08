package com.github.tacowasa059.advancementcopyrightplugin.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.util.io.BukkitObjectInputStream;

public class AchievementCount {
    public Objective objective;
    public AchievementCount(){
        init();
    }
    public void init(){
        org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        this.objective = scoreboard.getObjective("achieve-count");
        if (objective == null) objective = scoreboard.registerNewObjective("achieve-count", "dummy", ChatColor.GOLD +"実績数");
        for(Player player: Bukkit.getOnlinePlayers()){
            Score score=objective.getScore(player.getName());
            score.setScore(0);
        }
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }
    public void addScore(Player player){
        if(objective==null)init();
        if(objective.getScore(player.getName()).isScoreSet()){
            Score score = objective.getScore(player.getName());
            score.setScore(score.getScore()+1);
        }
        else{
            Score score = objective.getScore(player.getName());
            score.setScore(1);
        }
    }
    public void resetScore(){
        if(objective!=null){
            for (String scoreName : objective.getScoreboard().getEntries()) {
                objective.getScoreboard().resetScores(scoreName);
            }
            objective.unregister();//unregisterだけしてもプラグイン上ではスコアが残ってる
        }
        this.objective=null;
        init();
    }
}
