package me.pianopenguin.sessionlogin;

import me.pianopenguin.sessionlogin.command.SessionInfoCommand;
import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.command.CommandBus;

import java.util.logging.Logger;

public class Main implements ModInitializer {
    public static Logger LOGGER = Logger.getLogger("SessionLogin");
    @Override
    public void preInit() {
        System.out.println("Initializing Session Login");
        CommandBus.register(new SessionInfoCommand());
    }
}