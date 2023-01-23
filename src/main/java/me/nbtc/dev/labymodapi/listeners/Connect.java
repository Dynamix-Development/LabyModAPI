package me.nbtc.dev.labymodapi.listeners;

import me.nbtc.dev.labymodapi.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Connect implements Listener {
    List<Location> locationList = new ArrayList<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (Main.getInstance().getConfig().getBoolean("Options.VoiceChat.AskOnJoin") &&
                !Main.getInstance().getConfig().getBoolean("Options.Cinematic.PlayOnJoin")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Main.getInstance().getVoiceChat().sendSettings(event.getPlayer(), true, true);
                }
            }.runTaskLater(Main.getInstance(), 25L);
        }
        if (Main.getInstance().getConfig().getBoolean("Options.TabImage.Enabled")) {
            if (Main.getInstance().getConfig().getString("Options.TabImage.ImageLink").isEmpty() ||
                    Main.getInstance().getConfig().getString("Options.TabImage.ImageLink") == null) {
                Main.getInstance().log("&f&lLabyModAPI: &cInvaild tab image link!");
            }
            Main.getInstance().getTabImage().sendServerBanner(event.getPlayer());
        }
        if (Main.getInstance().getConfig().getBoolean("Options.LabyModChatGameModePlaying.Enabled")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Main.getInstance().getLabyModChatGameMode().sendCurrentPlayingGamemode(event.getPlayer(), true, Main.getInstance().getConfig().getString("Options.LabyModChatGameModePlaying.Playing"));
                }
            }.runTaskLater(Main.getInstance(), 1L);
        }
        if (Main.getInstance().getConfig().getBoolean("Options.Cinematic.PlayOnJoin")) {
            if (!locationList.isEmpty()) {
                Main.getInstance().getCinematicView().playCinematic(event.getPlayer(), locationList, Main.getInstance().getConfig().getLong("Options.Cinematic.DurationInLong"));
                return;
            }
            Configuration config = Main.getInstance().getCinematicYAML().getManager();
            ConfigurationSection locations = config.getConfigurationSection("Locations");
            if (locations == null) {
                voiceChatCheck(event.getPlayer());
                return;
            }
            for (String key : locations.getKeys(false)) {
                ConfigurationSection locationSection = locations.getConfigurationSection(key);
                World world = Bukkit.getServer().getWorld(locationSection.getString("world"));
                double x = locationSection.getDouble("x");
                double y = locationSection.getDouble("y");
                double z = locationSection.getDouble("z");
                float yaw = (float) locationSection.getDouble("yaw");
                float pitch = (float) locationSection.getDouble("pitch");
                Location location = new Location(world, x, y, z, yaw, pitch);
                locationList.add(location);
            }
            if (locationList.size() <= 1) {
                voiceChatCheck(event.getPlayer());
                Main.getInstance().log("&f&lLabyModAPI:&c At least two locations are required to start a cinematic!");
                return;
            }
            Main.getInstance().getCinematicView().playCinematic(event.getPlayer(), locationList, Main.getInstance().getConfig().getLong("Options.Cinematic.DurationInLong"));
        }
    }
    private void voiceChatCheck(Player player){
        if (Main.getInstance().getConfig().getBoolean("Options.VoiceChat.AskOnJoin")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Main.getInstance().getVoiceChat().sendSettings(player, true, true);
                }
            }.runTaskLater(Main.getInstance(), 25L);
        }
    }
}
