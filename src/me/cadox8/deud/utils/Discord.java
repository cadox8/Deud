package me.cadox8.deud.utils;

import me.cadox8.deud.entities.creatures.player.Player;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class Discord {

    public Discord() {
        final DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> Log.log(Log.LogType.SUCCESS, "Deud hooked into " + user.username + "#" + user.discriminator + "!")).build();

        DiscordRPC.discordInitialize("524279850887020544", handlers, true);
    }

    public void createNewPresence(Player p){
        final DiscordRichPresence.Builder rich = new DiscordRichPresence.Builder("Level: " + p.getLevel()).setDetails("Playing at map " + p.getAPI().getWorld().worldName());
        rich.setStartTimestamps(System.currentTimeMillis() / 1000);
        rich.setBigImage("icono_big", "DeudGame");
        DiscordRPC.discordUpdatePresence(rich.build());
    }
}
