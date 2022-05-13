package net.ehrenlos.befizzi.listeners;

import net.ehrenlos.befizzi.Community;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class SleepListener implements Listener {

    public double sleepPart = 1.0;
    public int requiredPlayerCount = 5;

    public int playersInBed;
    public boolean showWakeupMessage = true;

    private int getPlayersInBedNeeded(World world) {
        double playersNeededDouble = world.getPlayers().size() * this.sleepPart;
        int playersNeededInt = (int) playersNeededDouble;
        return playersNeededInt + (playersNeededDouble > playersNeededInt ? 1 : 0);
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();

        int playersInWorld = world.getPlayers().size();

        if (event.isCancelled() || world.getEnvironment() != World.Environment.NORMAL)
            return;

        this.playersInBed++;

        if (playersInWorld < this.requiredPlayerCount)
            return;

        int playersInBedNeeded = getPlayersInBedNeeded(world);

        world.getPlayers().forEach(player1 ->
                player1.sendMessage(String.format("§cEs befinden sich §7%d§8/§7%d §cSpieler im Bett zum schlafen", this.playersInBed, playersInBedNeeded)));

        if (playersInBedNeeded < this.playersInBed)
            return;

        Community.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Community.getInstance(), () -> {
            if (playersInBed < playersInBedNeeded)
                return;

            this.showWakeupMessage = false;
            Community.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Community.getInstance(), () -> this.showWakeupMessage = true, 20L);

            world.setTime(23450);

            if (world.hasStorm())
                world.setStorm(false);

            if (world.isThundering())
                world.setThundering(false);

            if (world.getWeatherDuration() > 0)
                world.setWeatherDuration(0);

            if (player.isSleeping())
                player.wakeup(true);
        }, 4 * 20L);
    }

    @EventHandler
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
        this.playersInBed--;
        World world = event.getPlayer().getWorld();

        if (world.getPlayers().size() < this.requiredPlayerCount)
            return;

        if (this.showWakeupMessage) {
            world.getPlayers().forEach(player ->
                    player.sendMessage(String.format("§cEs befinden sich §7%d§8/§7%d §cSpieler im Bett zum schlafen", this.playersInBed, this.getPlayersInBedNeeded(world))));
        }
    }
}
