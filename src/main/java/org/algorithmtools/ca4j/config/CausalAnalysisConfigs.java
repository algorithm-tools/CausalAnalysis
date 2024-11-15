package org.algorithmtools.ca4j.config;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CausalAnalysisConfigs {

    public static final Map<String, ConfigOption> configKeyMap = new HashMap<>();

    static {
        CausalAnalysisConfigs configs = new CausalAnalysisConfigs();
        Arrays.stream(FieldUtils.getAllFields(CausalAnalysisConfigs.class)).filter((f) -> {
            return ConfigOption.class.isAssignableFrom(f.getType());
        }).forEach((f) -> {
            try {
                ConfigOption co = (ConfigOption)f.get(configs);
                configKeyMap.put(co.getKey(), co);
            } catch (IllegalAccessException var4) {
            }
        });
    }

    public static void main(String[] args) {
        System.out.println(configKeyMap);
    }

}
