package de.schaack.ml.basics.util;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import org.yaml.snakeyaml.Yaml;

public class YamlParser {

    private YamlParser() {
    }

    public static Properties loadProperties(InputStream inputStream) {
        Map<String, Object> yaml = parseYaml(inputStream);
        return yamlMapToProperties(yaml);
    }

    public static Map<String, Object> parseYaml(InputStream inputStream) {
        Yaml yaml = new Yaml();
        return yaml.load(inputStream);
    }

    public static Properties yamlMapToProperties(Map<String, Object> yamlMap) {
        Properties properties = new Properties();
        flattenMap("", yamlMap, properties);
        return properties;
    }

    private static void flattenMap(String prefix, Map<String, Object> map, Properties properties) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();
            if (entry.getValue() instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> nestedMap = (Map<String, Object>) entry.getValue();
                flattenMap(key, nestedMap, properties);
            } else {
                properties.setProperty(key, String.valueOf(entry.getValue()));
            }
        }
    }
}