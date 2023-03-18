package app.common;

import java.util.Map;

public class EnvService {
    private static Map<String, String> env = System.getenv();

    public static String getEnv(String key){
        try{
            return EnvService.env.get(key);
        }
        catch (NullPointerException e){
            System.err.printf("Enviroment variable not found with %s", key);
        }
        return null;
    }
}
