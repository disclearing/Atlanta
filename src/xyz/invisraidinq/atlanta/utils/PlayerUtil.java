package xyz.invisraidinq.atlanta.utils;

import org.bukkit.entity.Player;
import xyz.invisraidinq.atlanta.Atlanta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerUtil {

        public static Collection<Player> getOnlinePlayers() {
            List<Player> players = new ArrayList<>();
            for(Player player : Atlanta.getInstance().getServer().getOnlinePlayers()) {
                players.add(player);
            }
            return players;
        }
    }
