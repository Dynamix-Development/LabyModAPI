package me.nbtc.dev.labymodapi;


import me.nbtc.dev.labymodapi.cmds.Laby;
import me.nbtc.dev.labymodapi.labyhook.LabyModChatGameMode;
import me.nbtc.dev.labymodapi.labyhook.TabImage;
import me.nbtc.dev.labymodapi.labyhook.cinematic.BlackScreen;
import me.nbtc.dev.labymodapi.labyhook.cinematic.CinematicView;
import me.nbtc.dev.labymodapi.labyhook.cinematic.CinematicYAML;
import me.nbtc.dev.labymodapi.listeners.Connect;
import me.nbtc.dev.labymodapi.util.TextHelper;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public final class Main extends JavaPlugin {
    private static Main instance;
    private CinematicView cinematicView;
    private CinematicYAML cinematicYAML;
    private BlackScreen blackScreen;
    private TabImage tabImage;
    private LabyModChatGameMode labyModChatGameMode;
    @Override
    public void onEnable() {
        init();
        loadConfig();
        listeners();
        cmds();
        log(
                "\n&c&m------------------------------\n" +
                "&aLabyModAPI Plugin enabled!" + "\n" +
                "&aPlugin made by: &cNBTC" + "\n" +
                "&a(https://docs.labymod.net/pages/server/introduction/)" + "\n" +
                "&c&m------------------------------");
    }
    @Override
    public void onDisable(){
        log(
                "\n&c&m------------------------------\n" +
                        "&cLabyModAPI Plugin disabled!" + "\n" +
                        "&aPlugin made by: &cNBTC" + "\n" +
                        "&a(https://docs.labymod.net/pages/server/introduction/)" + "\n" +
                        "&c&m------------------------------");
    }
    private void loadConfig() {
        if (!(new File(getDataFolder(), "config.yml")).exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    public void log(String message) {
        getServer().getConsoleSender().sendMessage(TextHelper.format(message));
    }
    private void init(){
        instance = this;
        cinematicView = new CinematicView();
        cinematicYAML = new CinematicYAML();
        blackScreen = new BlackScreen();
        tabImage = new TabImage();
        labyModChatGameMode = new LabyModChatGameMode();
    }
    private void listeners(){
        getServer().getPluginManager().registerEvents(new Connect(),this);
    }
    private void cmds(){
        getCommand("laby").setExecutor(new Laby());
    }
    public static Main getInstance() {
        return instance;
    }
    public CinematicView getCinematicView() {
        return cinematicView;
    }
    public CinematicYAML getCinematicYAML() {
        return cinematicYAML;
    }
    public BlackScreen getBlackScreen() {
        return blackScreen;
    }
    public TabImage getTabImage() {
        return tabImage;
    }
    public LabyModChatGameMode getLabyModChatGameMode() {
        return labyModChatGameMode;
    }
}
