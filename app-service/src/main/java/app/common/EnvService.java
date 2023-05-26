package app.common;

import java.util.Map;

public class EnvService {
    private static Map<String, String> env = System.getenv();

    public static String getEnv(String key, String defaultValue) {
        String value = EnvService.env.get(key);
        if (value != null) {
            return value;
        }
        if (defaultValue == null) {
            throw new NullPointerException(String.format("Env variable and default value %s is missing", key));
        }
        return defaultValue;
    }
}
