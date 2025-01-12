package i.fran2019.Solary.Commands;

import i.fran2019.BotMaster.API.implementations.Command;
import i.fran2019.BotMaster.API.implementations.Plugin;
import i.fran2019.BotMaster.BotMaster;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.text.DecimalFormat;

public class StatsCMD extends Command {
    public StatsCMD(String name) {
        super(name);
    }

    @Override
    public void onExecute(SlashCommandInteractionEvent e) {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long allocatedMemory = memoryBean.getHeapMemoryUsage().getCommitted();
        long usedMemory = memoryBean.getHeapMemoryUsage().getUsed();

        double usedMemoryPercentage = (double) usedMemory / allocatedMemory * 100;

        DecimalFormat df = new DecimalFormat("#.##");

        StringBuilder message = new StringBuilder();
        message.append("**Bot General Information**\n");
        message.append("Bot Name: ").append(e.getJDA().getSelfUser().getName()).append("\n");
        message.append("Bot ID: ").append(e.getJDA().getSelfUser().getId()).append("\n");
        message.append("Number of Servers: ").append(e.getJDA().getGuildCache().size()).append("\n");
        message.append("Number of Users: ").append(e.getJDA().getUsers().size()).append("\n\n");

        message.append("**Plugins**\n");
        for (Plugin plugin : BotMaster.getBotMaster().getPluginManager().getPlugins()) {
            message.append("- ").append(plugin.getName()).append(": ").append(plugin.getDescription()).append("\n");
        }
        message.append("\n");

        message.append("**Java & Performance Stats**\n");
        message.append("Java Version: ").append(System.getProperty("java.version")).append("\n");
        message.append("Java Vendor: ").append(System.getProperty("java.vendor")).append("\n");
        message.append("Used Memory: ").append(df.format(usedMemory / (1024.0 * 1024))).append(" MB\n");
        message.append("Allocated Memory: ").append(df.format(allocatedMemory / (1024.0 * 1024))).append(" MB\n");
        message.append("Memory Usage: ").append(df.format(usedMemoryPercentage)).append("%\n");

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Bot Statistics");
        embed.setDescription(message.toString());

        e.replyEmbeds(embed.build()).queue();
    }
}
