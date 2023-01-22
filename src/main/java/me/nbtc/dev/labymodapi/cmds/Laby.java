package me.nbtc.dev.labymodapi.cmds;

import me.nbtc.dev.labymodapi.Main;
import me.nbtc.dev.labymodapi.util.TextHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Laby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("labymodapi.use"))){
            sender.sendMessage(TextHelper.format("&4&l(!) &cThis server is running &fLabyHelper&c by&6 NBTC"));
            return true;
        }
        if (args.length < 2){
            sender.sendMessage(TextHelper.format("&8&m----------------------"));
            sender.sendMessage(TextHelper.format("&f&lLabyModAPI: &f/laby cinematic addLocation&7 Add locations for the cinematic movement when you join the server"));
            sender.sendMessage(TextHelper.format("&f&lLabyModAPI: &f/laby cinematic resetLocations&7 Reset all the locations and remake it again"));
            sender.sendMessage(TextHelper.format("&aPlugin made by &cNBTC"));
            sender.sendMessage(TextHelper.format("&8&m----------------------"));
            return true;
        }
        Player p = (Player) sender;
        if (args[0].equalsIgnoreCase("cinematic") && args[1].equalsIgnoreCase("addlocation")){
            int size = 1;
            if (Main.getInstance().getCinematicYAML().getString("Locations").isEmpty()){
                Main.getInstance().getCinematicYAML().setObject("Locations."+size+".world", p.getLocation().getWorld().getName());
                Main.getInstance().getCinematicYAML().setObject("Locations."+size+".x", p.getLocation().getX());
                Main.getInstance().getCinematicYAML().setObject("Locations."+size+".y", p.getLocation().getY());
                Main.getInstance().getCinematicYAML().setObject("Locations."+size+".z", p.getLocation().getZ());
                Main.getInstance().getCinematicYAML().setObject("Locations."+size+".pitch", (float) p.getLocation().getPitch());
                Main.getInstance().getCinematicYAML().setObject("Locations."+size+".yaw", (float) p.getLocation().getYaw());
                sender.sendMessage(TextHelper.format("&f&lLabyModAPI:&a Location &6"+size+" &ahas been set!"));
                return true;
            }
            size = Main.getInstance().getCinematicYAML().getManager().getConfigurationSection("Locations.").getKeys(false).size()+1;
            Main.getInstance().getCinematicYAML().setObject("Locations."+size+".world", p.getLocation().getWorld().getName());
            Main.getInstance().getCinematicYAML().setObject("Locations."+size+".x", p.getLocation().getX());
            Main.getInstance().getCinematicYAML().setObject("Locations."+size+".y", p.getLocation().getY());
            Main.getInstance().getCinematicYAML().setObject("Locations."+size+".z", p.getLocation().getZ());
            Main.getInstance().getCinematicYAML().setObject("Locations."+size+".pitch", (float) p.getLocation().getPitch());
            Main.getInstance().getCinematicYAML().setObject("Locations."+size+".yaw", (float) p.getLocation().getYaw());
            sender.sendMessage(TextHelper.format("&f&lLabyModAPI: &aLocation &6"+size+" &ahas been set!"));
        }
        if (args[0].equalsIgnoreCase("cinematic") && args[1].equalsIgnoreCase("resetLocations")){
            Main.getInstance().getCinematicYAML().setDefaults();
            sender.sendMessage(TextHelper.format("&f&lLabyModAPI: &aLocations has been reset! Set the locations again and reload your server!"));
        }
        return false;
    }
}
