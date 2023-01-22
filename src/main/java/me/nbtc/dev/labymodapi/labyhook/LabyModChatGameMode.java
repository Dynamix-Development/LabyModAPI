package me.nbtc.dev.labymodapi.labyhook;


import at.julian.labymodapi.LabyModProtocol;
import com.google.gson.JsonObject;
import me.nbtc.dev.labymodapi.util.TextHelper;
import org.bukkit.entity.Player;

public class LabyModChatGameMode {
    public void sendCurrentPlayingGamemode(Player player, boolean visible, String gamemodeName ) {
        JsonObject object = new JsonObject();
        object.addProperty( "show_gamemode", visible );
        object.addProperty( "gamemode_name", TextHelper.format(gamemodeName) );
        LabyModProtocol.sendLabyModMessage( player, "server_gamemode", object );
    }
}