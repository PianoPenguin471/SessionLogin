package me.pianopenguin.sessionlogin.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.pianopenguin.sessionlogin.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

public class MojangAPI {
    public static String getUsername(String uuid) {
        try {
            // get profile data from mojang api
            StringBuilder result = new StringBuilder();
            HttpURLConnection urlConnection = (HttpURLConnection) new URL("https://api.mojang.com/user/profile/" + uuid).openConnection();
            urlConnection.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()))) {
                for (String line; (line = reader.readLine()) != null; ) {
                    result.append(line);
                }
            }
            JsonObject output = new JsonParser().parse(result.toString()).getAsJsonObject();
            return output.get("name").getAsString();
        } catch (Exception e) {
            Main.LOGGER.log(Level.WARNING, e.getMessage(), e);
            return null;
        }
    }
}
