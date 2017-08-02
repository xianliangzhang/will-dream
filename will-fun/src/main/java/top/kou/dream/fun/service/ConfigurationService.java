package top.kou.dream.fun.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZXL on 2017/8/1.
 */
@Service
public class ConfigurationService {
    private static final Configuration container = new Configuration();

    public String get(String key) {
        return container.get(key);
    }

    public String set(String key, String value) {
        return container.set(key, value);
    }

    static class Configuration {
        private Map<String, String> container = new HashMap<>();

        String get(String key) {
            return container.get(key);
        }

        String set(String key, String value) {
            String oldValue = container.get(key);
            container.put(key, value);
            return oldValue;
        }
    }
}
