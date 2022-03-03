package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.manager.BanManager;
import net.ehrenlos.bungeesystem.utils.TimeUnit;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

public class tempbanCommand extends Command {

    public tempbanCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("bungeesystem.tempban") || sender.hasPermission("bungeesystem.*")) {
            if (args.length >= 4) {
                final String target = args[0];
                if (BanManager.playerExistsBanned(BanManager.getUUID(target))) {
                    if (ProxyServer.getInstance().getPlayer(target) != null) {
                        final ProxiedPlayer player = ProxyServer.getInstance().getPlayer(target);
                        if (player.hasPermission("bungeesystem.tempban") || player.hasPermission("bungeesystem.*")) {
                            sender.sendMessage("§cDu kannst §e%target%§c nicht bannen!".replaceAll("%target%", target));
                            return;
                        }
                    }
                    if (BanManager.isBanned(BanManager.getUUID(target), target)) {
                        sender.sendMessage("§cDer Spieler §e%target% §cist bereits gebannt!".replaceAll("%target%", target));
                        return;
                    }
                    long value = 0L;
                    try {
                        value = Integer.parseInt(args[1]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(BungeeSystem.getPrefix() + "§cThe §e<Time> §cmust be a §enumber§c!");
                    }
                    if (value >= 91L) {
                        sender.sendMessage(BungeeSystem.getPrefix() + "§cThe §emaximum §cof §e<TimeUnit> §cis §e90§c!");
                    }
                    final String unitString = args[2];
                    String reason = "";
                    for (int i = 3; i < args.length; ++i) {
                        reason = reason + args[i] + " ";
                    }
                    final List<String> unitsList = TimeUnit.getUnitsAsString();
                    if (unitsList.contains(unitString.toLowerCase())) {
                        final TimeUnit unit = TimeUnit.getUnit(unitString);
                        final long seconds;
                        seconds = value * unit.getToSeconds();
                        BanManager.ban(sender.getName(), BanManager.getUUID(target), target, reason, seconds);
                        sender.sendMessage("§aDu hast §e%target% §afür §e%reason% §e%time% §avom Netzwerk gebannt!".replaceAll("%target%", target).replaceAll("%reason%", reason).replaceAll("%time%", "§e" + BanManager.getRemainingTime(BanManager.getUUID(target))));
                    } else {
                        sender.sendMessage(BungeeSystem.getPrefix() + "§cThe unit §e<TimeUnit> §cdoes not exist!");
                    }
                    for (final ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                        if ((players.hasPermission("bungeesystem.staffnotify") || players.hasPermission("bungeesystem.*"))) {
                            players.sendMessage("§7Der Spieler §e%player% §7wurde von §e%staff% §7für §e%reason% §e%time% §7vom Netzwerk gebannt!".replaceAll("%player%", target).replaceAll("%staff%", sender.getName()).replaceAll("%reason%", reason).replaceAll("%time%", "§e" + BanManager.getRemainingTime(BanManager.getUUID(target))));
                        }
                    }
                } else {
                    sender.sendMessage("§cDer Spieler §e%target% §cwurde nicht gefunden!".replaceAll("%target%", target));
                }
            } else {
                sender.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/tempban <Player> <Time> <TimeUnit> <Reason>"));
            }
        } else {
            sender.sendMessage(BungeeSystem.getPrefix() + BungeeSystem.getNoPerms());
        }
    }
}
