package me.pianopenguin.sessionlogin.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.pianopenguin.sessionlogin.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;

public class SessionUtils {

    public static void setSession(Session session) {
        try {
            Field sessionField = Minecraft.class.getDeclaredField("session");
            sessionField.setAccessible(true);
            sessionField.set(Minecraft.getMinecraft(), session);
            sessionField.setAccessible(false);
        } catch (Exception e) {
            Main.LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
    public static void setSession(String username, String playerId, String token) {
        setSession(new Session(username, playerId, token, "mojang"));
    }

    public static void setSession(String token) {
        try {
            if (token.startsWith("token:")) {
                token = token.substring(5);
            }

            // Split token string
            String[] tokenParts = token.split("\\.");
            if (tokenParts.length < 3) return; // Unsupported format

            // Base64 decode second part of token to get account data that includes uuid
            JsonObject accountDataJson = new JsonParser().parse(Arrays.toString(Base64.getDecoder().decode(tokenParts[1]))).getAsJsonObject();

            // get player id from json data
            String playerId = accountDataJson.get("profiles").getAsJsonObject().get("mc").getAsString().replace("-", "");

            // get player username from mojang api
            String username = MojangAPI.getUsername(playerId);

            setSession(username, playerId, token);
        } catch (Exception e) {
            Main.LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
