package me.moontimer.coinsystem.listener;

import me.moontimer.coinsystem.CoinSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CoinSystem.getInstance().getCoinAPI().loadCoins(player);
    }
}
