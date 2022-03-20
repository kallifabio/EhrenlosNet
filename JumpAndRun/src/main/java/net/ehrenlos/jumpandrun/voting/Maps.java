package net.ehrenlos.jumpandrun.voting;

import net.ehrenlos.jumpandrun.JumpAndRun;
import net.ehrenlos.jumpandrun.gamestates.LobbyState;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Maps {

    private String name;
    private String builder;
    private Location[] spawnLocations = new Location[LobbyState.MAX_PLAYERS];
    private Location spectatorLocation;
    private Location location;

    private int votes;

    public Maps(String name) {
        this.name = name.toUpperCase();

        if (exists()) {
            builder = JumpAndRun.getInstance().getConfig().getString("Arenas." + name + ".Builder");
        }
    }

    //<editor-fold defaultstate="collapsed" desc="create">
    public void create(String builder) {
        this.builder = builder;
        JumpAndRun.getInstance().getConfig().set("Arenas." + name + ".Builder", builder);
        JumpAndRun.getInstance().saveConfig();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="load">
    public void load() {
        for (int i = 0; i < spawnLocations.length; i++) {
            //spawnLocations[i] = LocationManager.getLobbyLocation("Arenas." + name + "." + (i + 1));
        }
        //spectatorLocation = LocationManager.getLobbyLocation("Arenas." + name + ".Spectator");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="exists">
    public boolean exists() {
        return (JumpAndRun.getInstance().getConfig().getString("Arenas." + name + ".Builder") != null);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="playable">
    public boolean playable() {
        ConfigurationSection configSection = JumpAndRun.getInstance().getConfig().getConfigurationSection("Arenas." + name);
        if (!configSection.contains("Spectator")) {
            return false;
        }
        if (!configSection.contains("Builder")) {
            return false;
        }
        for (int i = 1; i < LobbyState.MAX_PLAYERS + 1; i++) {
            if (!configSection.contains(Integer.toString(i))) {
                return false;
            }
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setSpawnLocation">
    public void setSpawnLocation(int spawnNumber, Player player) {
        spawnLocations[spawnNumber - 1] = location;
        //LocationManager.setLobbyLocation("Arenas." + name + "." + spawnNumber, player.getLocation());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="addVote">
    public void addVote() {
        votes++;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="removeVote">
    public void removeVote() {
        votes--;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getName">
    public String getName() {
        return name;
    }
    //</editor-fold>

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getBuilder">
    public String getBuilder() {
        return builder;
    }
    //</editor-fold>

    public void setBuilder(String builder) {
        this.builder = builder;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getSpawnLocations">
    public Location[] getSpawnLocations() {
        return spawnLocations;
    }
    //</editor-fold>

    public void setSpawnLocations(Location[] spawnLocations) {
        this.spawnLocations = spawnLocations;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getSpectatorLocation">
    public Location getSpectatorLocation() {
        return spectatorLocation;
    }

    //<editor-fold defaultstate="collapsed" desc="setSpectatorLocation">
    public void setSpectatorLocation(Player player) {
        spectatorLocation = location;
        //LocationManager.setLobbyLocation("Arenas." + name + ".Spectator", player.getLocation());
    }

    //<editor-fold defaultstate="collapsed" desc="getVotes">
    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
