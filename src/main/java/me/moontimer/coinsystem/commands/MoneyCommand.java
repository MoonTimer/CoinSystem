package me.moontimer.coinsystem.commands;

import me.moontimer.coinsystem.CoinSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        switch (args.length) {
            case 0:
                player.sendMessage("§7[§2Lemon§eVace§7] §8Du hast zurzeit §e" + CoinSystem.getInstance().getCoinAPI().getCoins(player.getUniqueId()) + "€");
                break;
            case 3:
                if (!player.hasPermission("money.change")) {
                    if (args[0].equalsIgnoreCase("pay")) {
                        CoinSystem.getInstance().getCoinAPI().removeCoins(player.getUniqueId(), Integer.parseInt(args[2]));
                        CoinSystem.getInstance().getCoinAPI().addCoins(Bukkit.getPlayer(args[1]).getUniqueId(), Integer.parseInt(args[2]));
                        player.sendMessage("§7[§2Lemon§eVace§7] §8Du hast dem Spieler §e" + args[2] + "€ §8überwiesen");
                        Bukkit.getPlayer(args[1]).sendMessage("§7[§2Lemon§eVace§7] §8Dir wurden §e" + args[2] + "€ §8 von §a" + player.getName() + " §8überwiesen");
                    }
                    player.sendMessage("§7[§2Lemon§eVace§7] §8Du hast keine Rechte auf diesen Befehl");
                    return true;
                }
                switch (args[0].toLowerCase()) {
                    case "add":
                        CoinSystem.getInstance().getCoinAPI().addCoins(Bukkit.getPlayer(args[1]).getUniqueId(), Integer.parseInt(args[2]));
                        player.sendMessage("§7[§2Lemon§eVace§7] §8Du hast den Spieler §e" + CoinSystem.getInstance().getCoinAPI().getCoins(player.getUniqueId()) + "€ §8gegeben");
                        break;
                    case "remove":
                        CoinSystem.getInstance().getCoinAPI().removeCoins(Bukkit.getPlayer(args[1]).getUniqueId(), Integer.parseInt(args[2]));
                        player.sendMessage("§7[§2Lemon§eVace§7] §8Du hast den Spieler §e" + CoinSystem.getInstance().getCoinAPI().getCoins(player.getUniqueId()) + "€ §8entfernt");
                        break;
                    case "set":
                        CoinSystem.getInstance().getCoinAPI().setCoins(Bukkit.getPlayer(args[1]).getUniqueId(), Integer.parseInt(args[2]));
                        player.sendMessage("§7[§2Lemon§eVace§7] §8Du hast den Spieler auf §e" + CoinSystem.getInstance().getCoinAPI().getCoins(player.getUniqueId()) + "€ §8gesetzt");
                        break;
                    default:
                        player.sendMessage("§7[§2Lemon§eVace§7] §cFalscher Syntax §8Nur /money (add|remove|set)");
                        break;
                }
        }
        return false;
    }
}
