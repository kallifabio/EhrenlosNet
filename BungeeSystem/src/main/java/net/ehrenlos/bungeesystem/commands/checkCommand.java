package net.ehrenlos.bungeesystem.commands;

import net.ehrenlos.bungeesystem.BungeeSystem;
import net.ehrenlos.bungeesystem.manager.BanManager;
import net.ehrenlos.bungeesystem.manager.MuteManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class checkCommand extends Command {

    public checkCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("bungeesystem.check")) {
            if (args.length == 1) {
                final String player = args[0];
                if (BanManager.playerExistsBanned(BanManager.getUUID(player)) && MuteManager.playerExistsMuted(MuteManager.getUUID(player))) {
                    sender.sendMessage("§7Name §8» §e%player%".replaceAll("%player%", player));
                    //if (BanManager.isBanned(BanManager.getUUID(player), player) && BanManager.getEnd(BanManager.getUUID(player)) != -2L) {
                    sender.sendMessage("§7Gebannt §8» §aJa");
                    //sender.sendMessage("§7Dauer §8» §e%time%".replaceAll("%time%", BanManager.getRemainingTime(BanManager.getUUID(player))));
                    sender.sendMessage("§7Grund §8» §e%reason%".replaceAll("%reason%", BanManager.getReason(BanManager.getUUID(player))));
                    sender.sendMessage("§7Gebannt von §8» §e%staff%".replaceAll("%staff%", BanManager.getTeamBanned(BanManager.getUUID(player))));
                } else {
                    sender.sendMessage("§7Gebannt §8» §cNein");
                }
                sender.sendMessage("§f");
                if (MuteManager.isMuted(MuteManager.getUUID(player)) && MuteManager.getEnd(MuteManager.getUUID(player)) != -2L) {
                    sender.sendMessage("§7Gemutet §8» §aJa");
                    sender.sendMessage("§7Dauer §8» §e%time%".replaceAll("%time%", MuteManager.getRemainingTime(MuteManager.getUUID(player))));
                    sender.sendMessage("§7Grund §8» §e%reason%".replaceAll("%reason%", MuteManager.getReason(MuteManager.getUUID(player))));
                    sender.sendMessage("§7Gemutet von §8» §e%staff%".replaceAll("%staff%", MuteManager.getTeamMuted(MuteManager.getUUID(player))));
                } else {
                    sender.sendMessage("§7Gemutet §8» §cNein");
                }
            } else {
                //sender.sendMessage("§cDer Spieler §e%target% §cwurde nicht gefunden!".replaceAll("%target%", player));
            }
        } else {
            sender.sendMessage(BungeeSystem.getPrefix() + "§cBenutze: §e%command%".replaceAll("%command%", "/check <Spieler>"));
        }
        //} else {
        //sender.sendMessage(BungeeSystem.getPrefix() + BungeeSystem.getNoPerms());
    }
}

