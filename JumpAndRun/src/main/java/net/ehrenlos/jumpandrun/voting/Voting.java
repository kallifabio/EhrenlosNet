package net.ehrenlos.jumpandrun.voting;

import net.ehrenlos.jumpandrun.JumpAndRun;
import net.ehrenlos.jumpandrun.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Voting {

    public static final int MAP_AMOUNT = 2;

    private ArrayList<Maps> maps;
    private Maps[] votingMaps;
    private int[] votingInventoryOrder = new int[]{3, 5};
    private HashMap<String, Integer> playerVotes;
    private Inventory votingInventory;

    public Voting(ArrayList<Maps> maps) {
        this.maps = maps;
        votingMaps = new Maps[Voting.MAP_AMOUNT];
        playerVotes = new HashMap<>();

        selectRandomMaps();
        registerVotingInventory();
    }

    //<editor-fold defaultstate="collapsed" desc="selectRandomMaps">
    private void selectRandomMaps() {
        for (int i = 0; i < votingMaps.length; i++) {
            Collections.shuffle(maps);
            votingMaps[i] = maps.remove(0);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="registerVotingInventory">
    public void registerVotingInventory() {
        votingInventory = Bukkit.createInventory(null, 9, "§6§lVoting");

        for (int i = 0; i < votingMaps.length; i++) {
            Maps currentMap = votingMaps[i];
            votingInventory.setItem(votingInventoryOrder[i], new ItemBuilder(Material.PAPER).setDisplayName("§6" + currentMap.getName() + "§c - §c§l" + currentMap.getVotes() + " Votes").build());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getWinnerMap">
    public Maps getWinnerMap() {
        Maps winnerMap = votingMaps[0];
        for (int i = 1; i < votingMaps.length; i++) {
            if (votingMaps[i].getVotes() >= winnerMap.getVotes()) {
                winnerMap = votingMaps[i];
            }
        }
        return winnerMap;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="vote">
    public void vote(Player player, int votingMap) {
        if (!playerVotes.containsKey(player.getName())) {
            votingMaps[votingMap].addVote();
            player.closeInventory();
            player.sendMessage(JumpAndRun.getPrefix() + "§aDu hast für §9" + votingMaps[votingMap].getName() + " §aabgestimmt");
            playerVotes.put(player.getName(), votingMap);
            registerVotingInventory();
        } else {
            player.sendMessage(JumpAndRun.getPrefix() + "§cDu hast bereits gevotet");
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getVotingMaps">
    public Maps[] getVotingMaps() {
        return votingMaps;
    }
    //</editor-fold>

    public void setVotingMaps(Maps[] votingMaps) {
        this.votingMaps = votingMaps;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getPlayerVotes">
    public HashMap<String, Integer> getPlayerVotes() {
        return playerVotes;
    }
    //</editor-fold>

    public void setPlayerVotes(HashMap<String, Integer> playerVotes) {
        this.playerVotes = playerVotes;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getVotingInventory">
    public Inventory getVotingInventory() {
        return votingInventory;
    }

    public void setVotingInventory(Inventory votingInventory) {
        this.votingInventory = votingInventory;
    }

    //<editor-fold defaultstate="collapsed" desc="getVotingInventoryOrder">
    public int[] getVotingInventoryOrder() {
        return votingInventoryOrder;
    }

    public void setVotingInventoryOrder(int[] votingInventoryOrder) {
        this.votingInventoryOrder = votingInventoryOrder;
    }

    public ArrayList<Maps> getMaps() {
        return maps;
    }

    public void setMaps(ArrayList<Maps> maps) {
        this.maps = maps;
    }
}
