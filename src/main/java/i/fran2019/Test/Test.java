package i.fran2019.Test;

import i.fran2019.BotMaster.API.implementations.Plugin;
import i.fran2019.BotMaster.BotMaster;
import i.fran2019.Solary.Commands.StatsCMD;
import i.fran2019.Test.Commands.TestCMD;
import i.fran2019.Test.Manager.ButtonClickEvent;
import i.fran2019.Test.Manager.ConfigManagerTest;
import lombok.Getter;

@Getter public class Test extends Plugin {
    private final Test test = this;
    private ConfigManagerTest configManagerTest;

    public Test(BotMaster botMaster, String name, String description, String version) {
        super(botMaster, name, description, version);
    }

    @Override
    public void onEnable() {
        configManagerTest = new ConfigManagerTest(getConfigManager());

        getBotMaster().getCommandManager().registerCommand(new StatsCMD("stats"));
        getBotMaster().getCommandManager().registerCommand(new TestCMD(configManagerTest.CONFIG_1));

        getBotMaster().getJda().addEventListener(new ButtonClickEvent());

        getLogger().info("Plugin Loaded");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled");
    }
}