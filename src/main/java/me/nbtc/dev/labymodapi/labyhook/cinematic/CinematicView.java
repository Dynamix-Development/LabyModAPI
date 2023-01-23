package me.nbtc.dev.labymodapi.labyhook.cinematic;

import at.julian.labymodapi.LabyModProtocol;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.nbtc.dev.labymodapi.Main;
import me.nbtc.dev.labymodapi.util.TextHelper;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class CinematicView {
    public void playCinematic(Player player, List<Location> points, long duration) {
        final boolean[] running = {true};
        player.setGameMode(GameMode.valueOf(Main.getInstance().getConfig().getString("Options.Cinematic.GameModeBefore")));
        JsonObject cinematic = new JsonObject();
        JsonArray pointss = new JsonArray();
        for (Location location : points) {
            JsonObject point = new JsonObject();
            point.addProperty("x", location.getX());
            point.addProperty("y", location.getY());
            point.addProperty("z", location.getZ());
            point.addProperty("yaw", location.getYaw());
            point.addProperty("pitch", location.getPitch());
            point.addProperty("tilt", 0);
            pointss.add(point);
        }
        cinematic.add("points", pointss);
        cinematic.addProperty("duration", duration);
        new BukkitRunnable(){
            @Override
            public void run() {
                player.setGameMode(GameMode.valueOf(Main.getInstance().getConfig().getString("Options.Cinematic.GameModeAfter")));
                player.setAllowFlight(false);
                player.setFlying(false);
                if (Main.getInstance().getConfig().getBoolean("Options.VoiceChat.AskOnJoin")){
                    Main.getInstance().getVoiceChat().sendSettings(player, true, true);
                }
                running[0] =false;
            }
        }.runTaskLater(Main.getInstance(), duration/50);
        if (Main.getInstance().getConfig().getBoolean("Options.Cinematic.Sound")) {
            String[] notes = {"note.harp", "note.bd", "note.hat", "note.snare"};
            new BukkitRunnable() {
                int currentNote;

                @Override
                public void run() {
                    Location playerLocation = player.getLocation();
                    player.playSound(playerLocation, notes[currentNote], 1, 1);
                    currentNote++;
                    if (currentNote >= notes.length) {
                        currentNote = 0;
                    }
                    if (!running[0]) {
                        cancel();
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0L, 5L);
        }
        if (Main.getInstance().getConfig().getBoolean("Options.Cinematic.CinceScope")) {
            Main.getInstance().getBlackScreen().sendCineScope(player, 50, 1000);
            Main.getInstance().getBlackScreen().sendCineScope(player, 0, 1000);
        }
        if (Main.getInstance().getConfig().getBoolean("Options.Cinematic.Title.Enabled")){
            String title = Main.getInstance().getConfig().getString("Options.Cinematic.Title.TitleMessage");
            String subTitle = Main.getInstance().getConfig().getString("Options.Cinematic.Title.SubTitleMessage");
            TextHelper.sendTitle(player, 0, (int) duration/50,0,title,subTitle);
        }
        player.teleport(points.get(0));
        player.setAllowFlight(true);
        LabyModProtocol.sendLabyModMessage(player, "cinematic", cinematic);
    }
}
