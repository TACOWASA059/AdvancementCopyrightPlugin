package com.github.tacowasa059.advancementcopyrightplugin.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;


public class AchievementList {
    public Objective objective;
    public AchievementList(){
        init();
    }
    public void init(){
        org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        this.objective = scoreboard.getObjective("achievement");
        if (objective == null) objective = scoreboard.registerNewObjective("achievement", "dummy", ChatColor.GOLD +"達成実績");

    }
    public Boolean addScore(String search){
        if(objective==null)init();
        for (String scoreName :  objective.getScoreboard().getEntries()) {
            if (scoreName.equals(search)) {
                return false;
            }
        }
        Score score = objective.getScore(search);
        score.setScore(1);
        return true;
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
