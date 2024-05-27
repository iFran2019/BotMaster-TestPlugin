package i.fran2019.Test.Commands;

import i.fran2019.BotMaster.API.annotations.CommandOption;
import i.fran2019.BotMaster.API.implementations.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class TestCMD extends Command {
    public TestCMD(String name) {
        super(name);
    }

    @CommandOption(
            name = "Option1",
            description = "useless",
            required = true,
            type = OptionType.STRING,
            // They can be in those two formats. In the last example, there is only the Value and the name is the same.
            // choices = {"NAME|VALUE", "NAME"}
            choices = {"Test|test", "Test2|test2", "Test4"}
    )
    String option1;

    @Override
    public void onExecute(SlashCommandInteractionEvent e) {
        e.reply("¡Test! Output: "+option1).queue();
    }
}
