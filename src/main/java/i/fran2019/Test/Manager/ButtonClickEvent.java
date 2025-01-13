package i.fran2019.Test.Manager;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ButtonClickEvent extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("hello")) {
            event.reply("Hello :)").queue();
        } else if (event.getComponentId().equals("emoji")) {
            event.editMessage("That button didn't say click me").queue();
        }
    }
}
