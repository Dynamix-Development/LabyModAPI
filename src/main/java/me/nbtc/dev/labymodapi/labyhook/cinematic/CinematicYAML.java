package me.nbtc.dev.labymodapi.labyhook.cinematic;

import me.nbtc.dev.labymodapi.Main;
import me.nbtc.dev.labymodapi.util.YMLFile;

public class CinematicYAML extends YMLFile {
    public CinematicYAML() {
        super("cinematic.yml", Main.getInstance().getDataFolder().getPath());
        createNew();
    }

    @Override
    public void setDefaults() {
        setObject("Locations","");
    }
}
