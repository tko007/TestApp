package at.tko.collector.util;

import at.tko.collector.config.YamlFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Reads and parse the config yaml file.
 */
public class ConfigReader {

    // singleton approach
    private static ConfigReader configReader =
            new ConfigReader();

    public static ConfigReader getInstance(){
        return configReader;
    }

    // using URI for locating settings file
    private final String pathToYamlFile = "settings/config.yaml";

    private YamlFile configFile;

    private ConfigReader() {
        try {
            final ObjectMapper mapper =
                    new ObjectMapper(new YAMLFactory());
            configFile = mapper.readValue(
                    new File(Paths.get(pathToYamlFile).toString()),
                    YamlFile.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public YamlFile getConfigFile() {
        return configFile;
    }
}
