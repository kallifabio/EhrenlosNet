package net.ehrenlos.jumpandrun.listeners;

import net.ehrenlos.jumpandrun.JumpAndRun;
import net.ehrenlos.jumpandrun.countdowns.LobbyCountdown;
import net.ehrenlos.jumpandrun.gamestates.LobbyState;
import net.ehrenlos.jumpandrun.manager.ItemManager;
import net.ehrenlos.jumpandrun.manager.LocationManager;
import net.ehrenlos.jumpandrun.voting.Voting;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collection;

public class PlayerListener implements Listener {

    Player player;

    Collection<? extends Player> arrayOfPlayer = Bukkit.getOnlinePlayers();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        player = event.getPlayer();

        if (!(JumpAndRun.getGameStatesManager().getCurrentGameState() instanceof LobbyState)) {
            return;
        }

        event.setJoinMessage(JumpAndRun.getPrefix() + "§a" + player.getDisplayName() + " §8ist dem Spiel beigetreten §8[§2" + Bukkit.getOnlinePlayers().size() + "§8/§4" + LobbyState.MAX_PLAYERS + "§8]");

        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemManager(Material.CHEST).setDisplayName("§eMapVote").build());
        player.getInventory().setItem(8, new ItemManager(Material.SLIME_BALL).setDisplayName("§cSpiel verlassen").build());


        // Wichtige Methode //
        /*int j = arrayOfPlayer.size();
        for (int i = 0; i < j; i++) {
            Player online = (Player) arrayOfPlayer.toArray()[i];
            switch (i) {
                case 0:
                    online.teleport(LocationManager.getLobbyLocation("Java"));
                    break;
                case 1:
                    online.teleport(LocationManager.getLobbyLocation("HTML"));
                    break;
            }
        }*/

        player.teleport(LocationManager.getLobbyLocation());

        LobbyState lobbyState = (LobbyState) JumpAndRun.getGameStatesManager().getCurrentGameState();
        LobbyCountdown countdown = lobbyState.getLobbyCountdown();
        if (Bukkit.getOnlinePlayers().size() >= LobbyState.MIN_PLAYERS) {
            if (!countdown.isIsRunning()) {
                countdown.stopIdle();
                countdown.start();
            }
        }

        Voting voting = JumpAndRun.getInstance().getVoting();
        if (!voting.getPlayerVotes().containsKey(player.getName())) {
            voting.getVotingMaps()[voting.getPlayerVotes().get(player.getName())].addVote();
            voting.registerVotingInventory();
        }

        player.setGameMode(GameMode.SURVIVAL);
        player.setPlayerWeather(WeatherType.CLEAR);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        player = event.getPlayer();
        if (!(JumpAndRun.getGameStatesManager().getCurrentGameState() instanceof LobbyState)) {
            return;
        }

        event.setQuitMessage(JumpAndRun.getPrefix() + "§c" + player.getDisplayName() + " §8hat das Spiel verlassen §8[§2" + Bukkit.getOnlinePlayers().size() + "§8/§4" + LobbyState.MAX_PLAYERS + "§8]");

        LobbyState lobbyState = (LobbyState) JumpAndRun.getGameStatesManager().getCurrentGameState();
        LobbyCountdown countdown = lobbyState.getLobbyCountdown();
        if (Bukkit.getOnlinePlayers().size() < LobbyState.MIN_PLAYERS) {
            if (countdown.isIsRunning()) {
                countdown.stop();
                countdown.startIdle();
            }
        }

        Voting voting = JumpAndRun.getInstance().getVoting();
        if (voting.getPlayerVotes().containsKey(player.getName())) {
            voting.getVotingMaps()[voting.getPlayerVotes().get(player.getName())].removeVote();
            voting.getPlayerVotes().remove(player.getName());
            voting.registerVotingInventory();
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        player = (Player) event.getWhoClicked();
        event.setCancelled(true);
    }
}
