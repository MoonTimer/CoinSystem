package me.moontimer.coinsystem.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.moontimer.coinsystem.CoinSystem;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MoneyExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "money";
    }

    @Override
    public @NotNull String getAuthor() {
        return "MoonTimer";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null)
            return null;
        if (params.equals("getmoney")) {
            return String.valueOf(CoinSystem.getInstance().getCoinAPI().getCoins(player.getUniqueId()));
        }
        return null;
    }
}
