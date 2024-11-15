package org.algorithmtools.ca4j.pojo;

import org.algorithmtools.ca4j.config.CausalAnalysisConfigs;
import org.algorithmtools.ca4j.config.ConfigOption;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CausalAnalysisContext implements Serializable {

    private Map<ConfigOption, Object> configMap = new HashMap<>();

    public void putConfig(String key, Object value){
        if (!CausalAnalysisConfigs.configKeyMap.containsKey(key)) {
            throw new IllegalArgumentException("["+key+"] not exist!");
        }
        configMap.put(CausalAnalysisConfigs.configKeyMap.get(key), value);
    }

    public void putConfig(ConfigOption configOption, Object value){
        configMap.put(configOption, value);
    }

    public Object getConfig(String key){
        if (CausalAnalysisConfigs.configKeyMap.containsKey(key)) {
            return getConfig(CausalAnalysisConfigs.configKeyMap.get(key));
        } else {
            return null;
        }
    }

    public Object getConfig(ConfigOption configOption){
        return Optional.ofNullable(configMap.get(configOption)).orElseGet(configOption::getDefaultValue);
    }

    public static CausalAnalysisContext createDefault(){
        CausalAnalysisContext context = new CausalAnalysisContext();
        return context;
    }

}
