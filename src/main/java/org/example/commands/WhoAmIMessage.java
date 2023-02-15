package org.example.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

import static org.example.Discord4Shell.Discord4Shell.user;
import static org.example.commands.SelectTargetMessage.SelectedTarget;

public class WhoAmIMessage extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (event.getMessage().getContentRaw().equalsIgnoreCase("WhoAmI")) {
            if(Objects.equals(SelectedTarget, user)) {
                event.getChannel().sendMessage(SelectedTarget).queue();
            }
        }
    }
}
