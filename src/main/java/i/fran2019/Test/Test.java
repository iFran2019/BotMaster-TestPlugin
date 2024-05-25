package i.fran2019.Test;

import i.fran2019.BotMaster.API.implementations.Plugin;
import i.fran2019.BotMaster.BotMaster;
import i.fran2019.Test.Commands.TestCMD;
import lombok.Getter;

@Getter public class Test extends Plugin {
    private final Test test = this;

    public Test(BotMaster botMaster, String name, String description) {
        super(botMaster, name, description);
    }

    @Override
    public void onEnable() {
        getBotMaster().getCommandManager().registerCommand(new TestCMD("test2"));
        getLogger().info("Plugin Loaded");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled");
    }
}