package me.pianopenguin.sessionlogin.command;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Session;
import net.weavemc.loader.api.command.Command;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class SessionInfoCommand extends Command {
    public SessionInfoCommand() {
        super("sessioninfo", "session", "getsession");
    }

    @Override
    public void handle(@NotNull String[] args) {
        Session currentSession = Minecraft.getMinecraft().getSession();
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Username: " + currentSession.getUsername()));
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Player Id: " + currentSession.getPlayerID()));
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Token: " + currentSession.getToken()));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(currentSession.getToken()), null);
    }
}
