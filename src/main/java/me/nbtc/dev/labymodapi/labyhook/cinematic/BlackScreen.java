package me.nbtc.dev.labymodapi.labyhook.cinematic;

import at.julian.labymodapi.LabyModProtocol;
import com.google.gson.JsonObject;
import org.bukkit.entity.Player;

public class BlackScreen {
    public void sendCineScope(Player player, int coveragePercent, long duration ) {
        JsonObject object = new JsonObject();
        object.addProperty( "coverage", coveragePercent );
        object.addProperty( "duration", duration );
        LabyModProtocol.sendLabyModMessage( player, "cinescopes", object );
    }
}
