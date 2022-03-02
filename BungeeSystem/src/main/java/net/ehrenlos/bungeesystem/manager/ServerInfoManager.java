package net.ehrenlos.bungeesystem.manager;

import com.google.common.collect.Lists;
import net.ehrenlos.bungeesystem.BungeeSystem;
import net.md_5.bungee.api.ProxyServer;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ServerInfoManager {

    private static final List<String> totalPings = Lists.newCopyOnWriteArrayList();

    private static final List<String> last60SecondsPings = Lists.newCopyOnWriteArrayList();

    private static final List<UUID> registredUsers = Lists.newCopyOnWriteArrayList();

    private static int userRecord;

    public static List<String> getTotalPings() {
        return totalPings;
    }

    public static List<String> getLast60SecondsPings() {
        return last60SecondsPings;
    }

    public static List<UUID> getRegistredUsers() {
        return registredUsers;
    }

    public static int getUserRecord() {
        return userRecord;
    }

    public static void setUserRecord(int userRecord) {
        ServerInfoManager.userRecord = userRecord;
    }

    public static int getLoginProtectionLogins() {
        return 3;
    }

    static int counter = 0;

    private static int updatedTimes;

    private static int totalPlayers;

    public static void startScheduler() {
        ProxyServer.getInstance().getScheduler().schedule(BungeeSystem.getInstance(), () -> {
            if (counter >= 60) {
                BungeeSystem.getServerInfoManager();
                getLast60SecondsPings().clear();
                updatedTimes = 0;
                totalPlayers = ProxyServer.getInstance().getOnlineCount();
                counter = 0;
            }
            BungeeSystem.getServerInfoManager();
            updatedTimes++;
            totalPlayers += ProxyServer.getInstance().getOnlineCount();
            counter++;
        }, 0L, 1L, TimeUnit.SECONDS);
    }

    public int getCounter() {
        return counter;
    }

    public static int getTotalPlayers() {
        return totalPlayers;
    }

    public static int getUpdatedTimes() {
        return updatedTimes;
    }
}
