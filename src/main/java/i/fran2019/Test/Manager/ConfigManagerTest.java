package i.fran2019.Test.Manager;

import i.fran2019.BotMaster.API.Managers.ConfigManager;
import i.fran2019.BotMaster.BotMaster;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;

public class ConfigManagerTest {
    private final File config;

    public String CONFIG_1;

    public ConfigManagerTest(ConfigManager configManager) {
        this.config = configManager.configFile("config.yml");
        loadConfig();
    }

    private void loadConfig() {
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> configMap;

            if (!config.exists()) {
                if (config.createNewFile()) {
                    configMap = Map.of("command", "test4");
                    try (Writer writer = new FileWriter(config)) {
                        yaml.dump(configMap, writer);
                    }
                } else {
                    throw new IOException("Failed to create config file.");
                }
            } else {
                try (InputStream inputStream = Files.newInputStream(config.toPath())) {
                    configMap = yaml.load(inputStream);
                }

                if (configMap == null) {
                    configMap = Map.of("command", "test4");
                } else if (!configMap.containsKey("command")) {
                    configMap.put("command", "test4");
                }
                try (Writer writer = new FileWriter(config)) {
                    yaml.dump(configMap, writer);
                }
            }

            this.CONFIG_1 = (String) configMap.get("command");
        } catch (IOException e) {
            BotMaster.getLogger().error("IOException {}", e.toString());
        }
    }
}
