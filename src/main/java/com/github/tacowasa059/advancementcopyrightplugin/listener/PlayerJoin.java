package com.github.tacowasa059.advancementcopyrightplugin.listener;

import com.github.tacowasa059.advancementcopyrightplugin.AdvancementCopyrightPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        Player player=e.getPlayer();
        //入ってきたプレイヤーに対する処理
        Objective objective=AdvancementCopyrightPlugin.plugin.achievementCount.objective;
        if(objective!=null){
            if(!objective.getScore(player.getName()).isScoreSet()){
                //登録されてないとき
                //すなわちリセットされてから一度もログインしてないとき
                Score score = objective.getScore(player.getName());
                score.setScore(0);
                player.getInventory().clear();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"advancement revoke "+player.getName()+" everything");
            }
            for (String scoreName :  objective.getScoreboard().getEntries()) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"advancement grant "+player.getName()+" only "+scoreName);
            }
        }
    }
}
