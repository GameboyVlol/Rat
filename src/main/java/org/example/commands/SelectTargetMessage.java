package org.example.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.Objects;
import static org.example.Discord4Shell.Discord4Shell.bot;
import static org.example.Discord4Shell.Discord4Shell.user;
import net.dv8tion.jda.api.events.session.SessionDisconnectEvent;

public class SelectTargetMessage extends ListenerAdapter {
    public static String SelectedTarget = "none";
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }


        if (event.getMessage().getContentRaw().contains("SelectTarget")) {
            String[] message = event.getMessage().getContentRaw().split(" ");
            SelectedTarget = message[1];
            if (Objects.equals(SelectedTarget, user)) {
                event.getChannel().sendMessage("Logged In As " + SelectedTarget).queue();
                Guild guild = event.getGuild();
                guild.retrieveMemberById(bot.getSelfUser().getId()).queue(member -> {
                    member.modifyNickname(SelectedTarget).queue();

                });
            }
        }
    }
}
