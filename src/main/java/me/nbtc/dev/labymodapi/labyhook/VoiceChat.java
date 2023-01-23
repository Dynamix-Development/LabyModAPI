package me.nbtc.dev.labymodapi.labyhook;

import at.julian.labymodapi.LabyModProtocol;
import com.google.gson.JsonObject;
import me.nbtc.dev.labymodapi.Main;
import org.bukkit.entity.Player;

public class VoiceChat {
    public void sendSettings(Player player, boolean keepSettings, boolean required ) {
        JsonObject voicechatObject = new JsonObject();
        voicechatObject.addProperty( "keep_settings_on_server_switch", keepSettings );
        JsonObject requestSettingsObject = new JsonObject();
        requestSettingsObject.addProperty("required", required );
        JsonObject settingsObject = new JsonObject();
        if (Main.getInstance().getConfig().getBoolean("Options.VoiceChat.MicrophoneVolume.Limited")) {
            settingsObject.addProperty("microphoneVolume", Main.getInstance().getConfig().getInt("Options.VoiceChat.MicrophoneVolume.Limit"));
        }
        if (Main.getInstance().getConfig().getBoolean("Options.VoiceChat.SurroundRange.Limited")) {
            settingsObject.addProperty("surroundRange", Main.getInstance().getConfig().getInt("Options.VoiceChat.SurroundRange.Limit"));
        }
        if (Main.getInstance().getConfig().getBoolean("Options.VoiceChat.SurroundVolume.Limited")) {
            settingsObject.addProperty("surroundVolume", Main.getInstance().getConfig().getInt("Options.VoiceChat.SurroundVolume.Limit"));
        }
        requestSettingsObject.add("settings", settingsObject );
        voicechatObject.add("request_settings", requestSettingsObject);
        LabyModProtocol.sendLabyModMessage( player, "voicechat", voicechatObject );
    }
}
