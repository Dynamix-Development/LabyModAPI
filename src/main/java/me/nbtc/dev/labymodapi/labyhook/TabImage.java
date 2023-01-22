package me.nbtc.dev.labymodapi.labyhook;

import at.julian.labymodapi.LabyModProtocol;
import com.google.gson.JsonObject;
import me.nbtc.dev.labymodapi.Main;
import org.bukkit.entity.Player;

public class TabImage {
    public void sendServerBanner(Player player) {
        JsonObject object = new JsonObject();
        object.addProperty("url", Main.getInstance().getConfig().getString("Options.TabImage.ImageLink")); // Url of the image
        LabyModProtocol.sendLabyModMessage(player, "server_banner", object);
    }
}
